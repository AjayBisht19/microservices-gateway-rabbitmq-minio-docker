package com.filehandlerrabbitmqconsumer;

import java.io.ByteArrayInputStream;  
import java.io.InputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.filehandlerrabbitconsumer.config.MQConfig;


import java.io.ByteArrayInputStream; 

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs; 

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

	
	@RabbitListener(queues =MQConfig.Queue)
	public void listener2(FileDocument file) {
		
//		String[] split1 = file.split(",");
//		String[] filearr=new String[6];
//		int size=0;
//		for (String string : split1) {
//			filearr[size] = string.split(":",2)[1];
//			size++;
//		}
//		filearr[0]=filearr[0].substring(1, filearr[0].length()-1);
//		filearr[1]=filearr[1].substring(1, filearr[1].length()-1);
//		filearr[2]=filearr[2].substring(1, filearr[2].length()-1);
//		filearr[3]=filearr[3].substring(1, filearr[3].length()-1);
//		InputStream fileInputStream = new ByteArrayInputStream(filearr[4].substring(1, filearr[4].length()-1).getBytes());
//		filearr[5]=filearr[5].substring(1, filearr[5].length()-2);
//		
//		FileDocument fd= new FileDocument(filearr[0],filearr[1],filearr[2],filearr[3],filearr[5]);
//		System.out.println("id : "+fd.getId());
//		System.out.println("name : "+fd.getFileName());
//		System.out.println("time : "+fd.getCreatedAt());
//		System.out.println("type : "+fd.getType());
//		System.out.println("user : "+fd.getUser());
//		System.out.println(fileInputStream);


		InputStream byteArrayInputStream = new ByteArrayInputStream(file.getContent());
		uploadToMinio(byteArrayInputStream,file);
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
