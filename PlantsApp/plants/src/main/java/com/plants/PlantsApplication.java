package com.plants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.plants")
public class PlantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantsApplication.class, args);
	}

}
