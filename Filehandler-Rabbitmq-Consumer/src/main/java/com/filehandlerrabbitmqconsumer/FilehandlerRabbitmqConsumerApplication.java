package com.filehandlerrabbitmqconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FilehandlerRabbitmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilehandlerRabbitmqConsumerApplication.class, args);
	}

}
