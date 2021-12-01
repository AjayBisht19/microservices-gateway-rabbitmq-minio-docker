package com.springcloud; 

import org.springframework.boot.SpringApplication;    
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringCloudApplication{
	
	

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		// TODO Auto-generated method stub
//		return builder.sources(SpringCloudApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudApplication.class, args);
	}
	
	@GetMapping("/test/secure")
	public String test() {
		return "secure gateway working ";
	}
	
	@GetMapping("/test")
	public String test1() {
		return "test gateway working ";
	}
	
	
	@GetMapping("/test/open")
	public String testOpen() {
		return "insecure gateway working ";
	}

}
