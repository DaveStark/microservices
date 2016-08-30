package com.dave.microservices.webGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebGatewayApp {
	
	public static void main(String[] args) {
		System.out.println("Starting Web-Gateway .");
		SpringApplication.run(WebGatewayApp.class, args);
		System.out.println("Web-Gateway started.");
	}
	
}
