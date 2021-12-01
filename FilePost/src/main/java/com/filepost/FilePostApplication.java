package com.filepost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FilePostApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilePostApplication.class, args);
	}

}
