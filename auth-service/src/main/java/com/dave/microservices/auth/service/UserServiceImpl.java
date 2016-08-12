package com.dave.microservices.auth.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import com.dave.microservices.auth.domain.User;
import com.dave.microservices.auth.repository.UserRepository;

public class UserServiceImpl implements UserService{

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Inject
	private UserRepository userRepository;
	
	@Override
	public void create(User user) {
		User existing = userRepository.findByUserName(user.getUserName());
		Assert.isNull(existing, "user already exists: " + user.getUserName());
		
		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);
		userRepository.save(user);
		
		log.info("new user has been created: {}", user.getUserName());
	}

}
