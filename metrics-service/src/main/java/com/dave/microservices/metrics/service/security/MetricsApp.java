package com.dave.microservices.metrics.service.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dave.microservices.account.AccountApp;

@SpringBootApplication
public class MetricsApp {

	public static void main(String[] args) {
		SpringApplication.run(AccountApp.class, args);
	}

}
