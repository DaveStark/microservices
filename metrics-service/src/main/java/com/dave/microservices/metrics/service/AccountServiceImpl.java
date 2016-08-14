package com.dave.microservices.metrics.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.dave.microservices.metrics.client.AuthServiceClient;
import com.dave.microservices.metrics.domain.Account;
import com.dave.microservices.metrics.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/*
	 * @Autowired TODO private StatisticsServiceClient statisticsClient;
	 */

	@Inject
	private AuthServiceClient authClient;

	@Inject
	private AccountRepository repository;

	@Override
	public Account find(long id) {
		return repository.getOne(id);
	}

	@Override
	public long save(Account account) {
		Assert.hasLength(account.getName());
		account = repository.save(account);

		log.info("new account has been created: '" + account.getName() + "'");

		return account.getId();
	}

}
