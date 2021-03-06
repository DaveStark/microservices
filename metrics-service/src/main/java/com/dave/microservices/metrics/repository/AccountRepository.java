package com.dave.microservices.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dave.microservices.metrics.domain.Account;;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
