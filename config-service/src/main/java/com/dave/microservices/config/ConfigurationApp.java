package com.dave.microservices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationApp {

	public static void main(String[] args) {
		System.out.println("Starting...");
		SpringApplication.run(ConfigurationApp.class, args);
		System.out.println("\r\nStarted");
	}

}
