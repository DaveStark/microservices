package com.dave.microservices.auth.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 431173969924388926L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="role")
	private String role;
	
	@Column(name="datemodified")
	private Timestamp dateModified;
	
	@Column(name="dateCreated", insertable = true, updatable = false)
	private Timestamp dateCreated;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dateModified
	 */
	public Timestamp getDateModified() {
		return dateModified;
	}

	/**
	 * @param date_modified the date_modified to set
	 */
	@PreUpdate
	void setDateModified() {
		this.dateModified = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * @return the date_created
	 */
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param date_created the dateCreated to set
	 */
	@PrePersist
	public void setDateCreated() {
		this.dateModified = this.dateCreated = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
