package com.oracle.bank.transactionservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;

	@Column(name = "AMOUNT")
	private Long amount;
	
	@Column(name = "CURRENT_AMOUNT")
	private Long currentAmount;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Long currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
