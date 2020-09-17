package com.oracle.bank.accountservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.bank.accountservice.config.ResponseObject;
import com.oracle.bank.accountservice.dto.TransactionInfo;
import com.oracle.bank.accountservice.dto.UserInfo;
import com.oracle.bank.accountservice.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createAccount(@RequestBody @Valid UserInfo userInfo) {
		accountService.createAccount(userInfo);
		return ResponseObject.Ok(userInfo.getUsername());
	}
	
	@PostMapping("/startTransaction")
	public ResponseEntity<String> startTransaction(@RequestBody  TransactionInfo transactionInfo,Authentication authentication) {

		return ResponseObject.Ok(Boolean.toString(accountService.startTransaction(transactionInfo,authentication)));
	}
	
	
	
	
	

}
