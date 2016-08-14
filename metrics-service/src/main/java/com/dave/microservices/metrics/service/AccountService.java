package com.dave.microservices.metrics.service;

import com.dave.microservices.metrics.domain.Account;

public interface AccountService {

	/**
	 * Find an account by name
	 * @param id
	 * @return
	 */
	Account find(long id);
	
	
	/**
	 * Validates and saves an account record
	 * @param account
	 * @return returns the id of saved record
	 */
	long save(Account account);
}
