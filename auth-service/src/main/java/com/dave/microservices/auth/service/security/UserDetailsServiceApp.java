package com.dave.microservices.auth.service.security;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dave.microservices.auth.domain.User;
import com.dave.microservices.auth.domain.UserDetailsApp;
import com.dave.microservices.auth.repository.UserRepository;


@Component
@Qualifier("UserDetailsServiceApp")
public class UserDetailsServiceApp implements UserDetailsService {
	
	private final UserRepository userRepository;	

	@Inject
	public UserDetailsServiceApp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		}
		return new UserDetailsApp(user);
	}

}
