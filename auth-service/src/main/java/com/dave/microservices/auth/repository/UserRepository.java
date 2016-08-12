package com.dave.microservices.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dave.microservices.auth.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserName(String username);

}

