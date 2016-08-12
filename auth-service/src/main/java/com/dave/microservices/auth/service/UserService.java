package com.dave.microservices.auth.service;

import com.dave.microservices.auth.domain.User;

public interface UserService {
	void create(User user);
}
