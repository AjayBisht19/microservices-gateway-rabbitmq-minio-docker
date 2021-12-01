package com.consumer;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//
//@Component
//public class MQListener {
//
//
////	@RabbitListener(queues = MQConfig.Queue)
////	public void listener(String fileId) {
////		System.out.println("file saved by id "+ fileId);
////	}
//
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//
//	@RabbitListener(queues = "List_2")
//	public String listener2(FileDocument num) {
//
//		System.out.println("Received number is  "+ num);
//		num.setUser("hero");
//		int a=6/0;
//		return num.getUser();
////		rabbitTemplate.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY,num);
//
//	}
//}


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//import io.minio.BucketExistsArgs;
//import io.minio.MakeBucketArgs;
//import io.minio.MinioClient;
//import io.minio.PutObjectArgs;

@Component
public class MQListener {

	@Value("${minio.access.name}")
	String accessKey;
	@Value("${minio.access.secret}")
	String accessSecret;
	@Value("${minio.url}")
	String minioUrl;
	@Value("${minio.bucket.name}")
	String defaultBucketName;


	@RabbitListener(queues = MQConfig.Queue)
	public void listener2(FileDocument file) {
		uploadToMinio(new ByteArrayInputStream(file.getContent()),file);
	}

	private void uploadToMinio(InputStream multipartFile, FileDocument fd) {
		System.out.println("minio function " + defaultBucketName);
		try {
			MinioClient minioClient = MinioClient.builder().endpoint(minioUrl).credentials(accessKey, accessSecret)
					.build();

			boolean bucketExists = minioClient
					.bucketExists(BucketExistsArgs.builder().bucket(defaultBucketName).build());
			System.out.println("Bucket exist " + bucketExists);
			if (!bucketExists) {
				System.out.println("Bucket does not exist");

				minioClient.makeBucket(MakeBucketArgs.builder().bucket(defaultBucketName).build());
			}
			minioClient.putObject(PutObjectArgs.builder().bucket(defaultBucketName).object(fd.getId()+"_"+ fd.getFileName())
					.stream(multipartFile, -1, 10485760).contentType(fd.getType())
					.build());

		} catch (Exception e) {
			System.out.println("Error occurred: " + e);
		}

	}
}
