package com.design.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternsApplication.class, args);
	}
}
