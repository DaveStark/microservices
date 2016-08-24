package com.dave.microservices.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthApp {

	public static void main(String[] args){
		System.out.println("\r\nStarting Auth app...");
		SpringApplication.run(AuthApp.class, args);
		System.out.println("\r\nAuth app started");
	}

	//"$2a$10$lKOHvu1rVqm/HVmCKJMUPuSI1elMXAiWfMvQ7j2ffpbQHQUtjuMPa" =="@dm1n$tr470r" 	
}
