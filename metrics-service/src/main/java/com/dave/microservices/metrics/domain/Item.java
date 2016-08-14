package com.dave.microservices.metrics.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


public class Item {

	@Length(min=1, max=30)
	private String descripcion;
	
	@NotNull
	private int amount;
	
	@NotNull
	private BigDecimal value;
	
	@NotNull
	private Currency currency;
}
