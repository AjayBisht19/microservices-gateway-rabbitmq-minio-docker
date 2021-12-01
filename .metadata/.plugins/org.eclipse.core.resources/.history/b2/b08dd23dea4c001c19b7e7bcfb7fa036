package com.filehandler.serviceImpl;

import com.filehandler.controller.FileController; 
import com.filehandler.dao.FileDocument;
import com.filehandler.repository.FileRepository;
import com.filehandler.service.FileService;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Value("${minio.access.name}")
	String accessKey;
	@Value("${minio.access.secret}")
	String accessSecret;
	@Value("${minio.url}")
	String minioUrl;

	@Value("${minio.bucket.name}")
	String defaultBucketName;

	@Override
	public ResponseEntity<?> getFile(String fileId, String userId) {
		Optional<FileDocument> byId = fileRepository.findById(fileId);

		if (byId.isEmpty()) {
			return ResponseEntity.badRequest().body("No file found");
		} else {
			FileDocument fileDocument = byId.get();

			if (!isUserAuthorized(userId) && fileDocument.getUser() != userId) {
				return new ResponseEntity<>("Unauthorized request", HttpStatus.UNAUTHORIZED);
			}

			byte[] bytes = null;
			try {
				MinioClient minioClient = MinioClient.builder().endpoint(minioUrl).credentials(accessKey, accessSecret)
						.build();
				if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(defaultBucketName).build())) {
					return new ResponseEntity<>("specified bucket does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
				}

				InputStream stream = minioClient.getObject(
						GetObjectArgs.builder().bucket(defaultBucketName).object(fileDocument.getId()+"_"+fileDocument.getFileName()).build());
				bytes = stream.readAllBytes();

			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.set("Content-Disposition","attachment; filename=" +fileDocument.getFileName());
			httpHeaders.set("Content-Type", fileDocument.getType());

			return ResponseEntity.ok().headers(httpHeaders).body(bytes);

		}
	}

	@Override
	public ResponseEntity<?> getAllFilesByUser(String userId) {

		if (!isUserAuthorized(userId)) {
			return new ResponseEntity<>("Unauthorized request ", HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(fileRepository.findAllByUser(userId));
	}

	public boolean isUserAuthorized(String userId) {
		System.out.println(FileController.loggedInUser + " : " + userId);

		if (!userId.equals(FileController.loggedInUser)) {
			return false;
		}
		return true;
	}

}
