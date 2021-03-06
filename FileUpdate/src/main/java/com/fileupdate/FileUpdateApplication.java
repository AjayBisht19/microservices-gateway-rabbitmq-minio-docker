package com.fileupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUpdateApplication.class, args);
	}

}
