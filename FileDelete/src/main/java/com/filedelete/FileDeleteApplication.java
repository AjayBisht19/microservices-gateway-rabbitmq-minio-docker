package com.filedelete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileDeleteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileDeleteApplication.class, args);
	}

}
