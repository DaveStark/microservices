package com.dave.microservices.auth.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, name = "user_name")
	private String userName;
	
	@Column(nullable = false, name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name="date_modified")
	private Timestamp dateModified;
	
	@Column(name="date_created", insertable = true, updatable = false)
	private Timestamp dateCreated;
	
	private boolean active;

	@ManyToMany
	@JoinTable(name="user_role", joinColumns ={@JoinColumn(name="user_id")},
			inverseJoinColumns ={@JoinColumn(name="role_id")})
	private ArrayList<Role> roles;
	public User() {}
	
	public User(User user) {
		this.id = user.id;
		this.userName = user.userName;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.password = user.password;
		this.dateModified = user.dateModified;
		this.dateCreated = user.dateCreated;
		this.active = user.active;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the user_name to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirst_name() {
		return firstName;
	}

	/**
	 * @param first_name the firstName to set
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
	 * @param lastName the lastName to set
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
	 * @param password the password to set
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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Returns user's roles
	 */
	public ArrayList<Role> getRoles(){
		return roles;
	}

}
