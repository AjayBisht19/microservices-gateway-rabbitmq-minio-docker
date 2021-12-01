package com.fileupdate.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupdate.dao.FileDocument;
import com.fileupdate.repository.FileRepository;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;

@RestController
@CrossOrigin("*")
public class FileUpdateController {

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

	@PutMapping("/update-file/{fileId}")
	public ResponseEntity<?> updateFile(@RequestParam("file") MultipartFile multipartFile,
			@PathVariable("fileId") String fileId, @RequestParam("user") String userId) {
		Optional<FileDocument> byId = fileRepository.findById(fileId);
		if (byId.isEmpty()) {
			return ResponseEntity.badRequest().body("no file found");
		} else {
			FileDocument fileDocument = byId.get();
			try {
			MinioClient minioClient = MinioClient.builder().endpoint(minioUrl).credentials(accessKey, accessSecret)
					.build();

			if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(defaultBucketName).build())) {
				return new ResponseEntity<>("specified bucket does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			minioClient.removeObject(
					RemoveObjectArgs.builder().bucket(defaultBucketName).object(fileDocument.getId()+"_"+fileDocument.getFileName()).build());

			fileDocument.setFileName(multipartFile.getOriginalFilename());
			fileDocument.setType(multipartFile.getContentType());			
			
			minioClient.putObject(PutObjectArgs.builder().bucket(defaultBucketName).object(fileDocument.getId()+"_"+multipartFile.getOriginalFilename())
					.stream(multipartFile.getInputStream(), -1, 10485760).contentType(multipartFile.getContentType())
					.build());
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("in update service");

			return ResponseEntity.ok(fileRepository.save(fileDocument));
		}
	}	
}
