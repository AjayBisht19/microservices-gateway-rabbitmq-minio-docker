package com.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SonarQubeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonarQubeDemoApplication.class, args);
	}
	
	@GetMapping("")
	public String test() {
		System.out.println("hello");
		int f=0;
		return "Hello world!!";
	}

}
