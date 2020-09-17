package com.oracle.bank.accountservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Joby Wilson Mathews
 *
 */
@Entity
@Table(name = "ACCOUNT_INFO")
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "FIRST_NAME")
	@NotNull
	private String firstName;
	
	@Column(name = "LAST_NAME")
	@NotNull
	private String lastName;
	
	@Column(name = "PHONE_NUMBER")
	@NotNull
	private String phoneNumber;
	
	@Column(name = "EMAIL")
	@NotNull
	private String email;
	
	@Column(name = "USERNAME")
	@NotNull
	private String username;
	
	@Column(name = "PASSWORD")
	@NotNull
	private String password;
	
	@Column(name = "BALANCE")
	private Long balance = 0L;

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	


}
