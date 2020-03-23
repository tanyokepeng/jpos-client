package com.jpos.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jpos"})
public class JposClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JposClientApplication.class, args);
	}

}
