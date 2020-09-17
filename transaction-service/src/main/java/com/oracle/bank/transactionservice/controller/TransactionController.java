package com.oracle.bank.transactionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.bank.transactionservice.model.Transaction;
import com.oracle.bank.transactionservice.service.TransactionService;

/**
 * @author Joby Wilson Mathews
 *
 */
@RestController
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/list/{username}")
	public List<Transaction> getAllTransactionDetails(@PathVariable("username") String username) {
		
		return transactionService.getAllTransactionDetails(username);
		
	}

}
