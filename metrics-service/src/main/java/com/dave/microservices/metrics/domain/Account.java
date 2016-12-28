package com.dave.microservices.metrics.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private List<AccountDetail> accountDetails;
	
	@NotNull
	private Date modifiedDate;
	
	@NotNull
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "account_type_id")
	private AccountType accountType;

	//This is the account's owner uuid
	@NotNull
    @Column(name="owner_account")
	private String ownerAccount;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the items
	 */
	public List<AccountDetail> getDetails() {
		return accountDetails;
	}

	/**
	 * @param items the items to set
	 */
	public void setDetails(List<AccountDetail> items) {
		this.accountDetails = items;
	}

	/**
	 * @return the modifiedDate
	 */
	private Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	@PreUpdate
	private void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	@PrePersist
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getOwner() {
		return ownerAccount;
	}

	public void setOwner(String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
}
