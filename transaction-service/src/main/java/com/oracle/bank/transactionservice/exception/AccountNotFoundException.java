package com.oracle.bank.transactionservice.exception;

/**
 * @author Joby Wilson Mathews
 *
 */
public class AccountNotFoundException extends Exception {
	
	public AccountNotFoundException(String message) {
		super(message);
	}

}
