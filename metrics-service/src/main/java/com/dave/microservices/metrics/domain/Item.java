package com.dave.microservices.metrics.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="items")
public class Item implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Length(min=1, max=50)
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
