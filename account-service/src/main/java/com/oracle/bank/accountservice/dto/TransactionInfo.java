package com.oracle.bank.accountservice.dto;

/**
 * @author Joby Wilson Mathews
 *
 */
public class TransactionInfo {
	
	private String transactionType;
	
	private Long amount;
	
	private String token;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	

}
