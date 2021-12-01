package com.filedelete.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.filedelete.dao.FileDocument;
import com.filedelete.repository.FileRepository;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;

@RestController
@CrossOrigin("*")
public class FileDeleteController {

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

	@DeleteMapping("delete-file/{fileId}")
	public ResponseEntity<?> deleteFile(@PathVariable("fileId") String fileId, @RequestParam("user") String userId) {
		System.out.println("IN DELETE SERVICE");

		Optional<FileDocument> byId = fileRepository.findById(fileId);
		if (byId.isEmpty()) {
			return ResponseEntity.badRequest().body("no file found");
		} else {
			FileDocument fileDocument = byId.get();

			try {
				System.out.println("before minio");

				MinioClient minioClient = MinioClient.builder().endpoint(minioUrl).credentials(accessKey, accessSecret)
						.build();

				if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(defaultBucketName).build())) {
					return new ResponseEntity<>("specified bucket does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
				}

				minioClient.removeObject(RemoveObjectArgs.builder().bucket(defaultBucketName)
						.object(fileDocument.getId()+"_"+ fileDocument.getFileName()).build());

				fileRepository.delete(fileDocument);
				System.out.println("after minio");

			} catch (Exception e) {
				e.printStackTrace();
			}
			return ResponseEntity.ok("file deleted");

		}
	}
}
