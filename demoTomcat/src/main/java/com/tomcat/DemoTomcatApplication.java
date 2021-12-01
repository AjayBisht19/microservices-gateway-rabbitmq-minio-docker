package com.tomcat;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class DemoTomcatApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DemoTomcatApplication.class, args);
	}
	 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(DemoTomcatApplication.class);
	}

	
	@GetMapping("/test")
	public String get() {
		return "tomcat success!!";
	}
}
