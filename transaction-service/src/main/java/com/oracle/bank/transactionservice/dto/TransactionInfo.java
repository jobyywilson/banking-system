package com.oracle.bank.transactionservice.dto;

/**
 * @author Joby Wilson Mathews
 *
 */
public class TransactionInfo {
	
	private String transactionType;
	
	private Long amount;
	
	private String username;
	
	private String token;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
