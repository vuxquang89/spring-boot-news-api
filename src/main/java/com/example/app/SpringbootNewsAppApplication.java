package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)// Remove "Using default security password"										
public class SpringbootNewsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNewsAppApplication.class, args);
	}

}
