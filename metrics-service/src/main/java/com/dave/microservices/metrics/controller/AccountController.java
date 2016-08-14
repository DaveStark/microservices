package com.dave.microservices.metrics.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dave.microservices.metrics.domain.Account;
import com.dave.microservices.metrics.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Inject
	private AccountService accountService;
	
	
	//@PreAuthorize("")
	//see http://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html	
	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	public Account getAccountById(@PathVariable long id){
		return accountService.find(id);
	}
	
	@RequestMapping(path="/", method = RequestMethod.POST)
	public long createAccount(@Valid @RequestBody Account account){
		return accountService.save(account);
	}
	
}
