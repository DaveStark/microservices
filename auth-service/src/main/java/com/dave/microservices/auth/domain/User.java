package com.dave.microservices.auth.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3899228455164474288L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String uuid;

	@Column(nullable = false, name = "user_name")
	private String userName;

	@Column(nullable = false, name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Column(name = "date_modified")
	private Timestamp dateModified;

	@Column(name = "date_created", insertable = true, updatable = false)
	private Timestamp dateCreated;

	private boolean active;

	public User() {
	}

	public User(User user) {
		this.uuid = user.uuid;
		this.userName = user.userName;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.password = user.password;
		this.dateModified = user.dateModified;
		this.dateCreated = user.dateCreated;
		this.role = user.role;
		this.active = user.active;
	}

	@PrePersist
	public void initialize()
	{
		this.uuid = UUID.randomUUID().toString();
		this.dateModified = this.dateCreated = new Timestamp(System.currentTimeMillis());
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return this.uuid;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the user_name to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 * the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the dateModified
	 */
	public Timestamp getDateModified() {
		return dateModified;
	}

	/**
	 * the date_modified to set
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
	 * @param dateCreated
	 * the dateCreated to set
	 */

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Returns user's roles
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}
