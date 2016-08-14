package com.dave.microservices.metrics.domain;

public enum AccountType {
	
	Income("Income"),
	Expense("Expense");
	
	private String name;
	
	private AccountType(String name){
		this.name = name;
	}
}
