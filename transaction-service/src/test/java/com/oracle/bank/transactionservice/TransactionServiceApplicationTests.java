package com.oracle.bank.transactionservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import javax.crypto.SecretKey;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.oracle.bank.transactionservice.dto.TransactionInfo;
import com.oracle.bank.transactionservice.enums.Constant;
import com.oracle.bank.transactionservice.enums.TransactonType;
import com.oracle.bank.transactionservice.exception.AccountNotFoundException;
import com.oracle.bank.transactionservice.model.Transaction;
import com.oracle.bank.transactionservice.service.TransactionService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
@TestPropertySource(locations="classpath:test.properties")
class TransactionServiceApplicationTests {
	
	@Autowired
	TransactionService transactionService;
	
	@Value("${secret.key}")
	private String SECRET_KEY;
	

	
	public String getToken() {
		SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	    return Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "SHA256").setSubject("johnsmith")
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .compact();
	}
	
	public String getInvalidTokenWithUsername() {
		SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	    return Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "SHA256").setSubject("johnsmith122")
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .compact();
	}
	
	@Test
	public void testeCheckAccountNotFound() throws AccountNotFoundException {
		TransactionInfo transferInfo = new TransactionInfo();
		transferInfo.setAmount(100000L);
		transferInfo.setToken(getInvalidTokenWithUsername());
        transferInfo.setTransactionType("CARD");

        assertThrows(AccountNotFoundException.class, ()->{transactionService.processTransaction(transferInfo);} );
		
		
	}
	
	@Test
	public void testdCheckInvalidTransaction() throws AccountNotFoundException {
		TransactionInfo transferInfo = new TransactionInfo();
		transferInfo.setAmount(100000L);
		transferInfo.setToken(getToken());
        transferInfo.setTransactionType("CARD");
        Transaction transaction =transactionService.processTransaction(transferInfo);
		assertEquals(Constant.INVALID_TRANSACTION,transaction.getDescription());
		
	}
	
	@Test
	public void testcCheckInsufficentAmount() throws AccountNotFoundException {
		TransactionInfo transferInfo = new TransactionInfo();
		transferInfo.setAmount(100000L);
		transferInfo.setToken(getToken());
        transferInfo.setTransactionType(TransactonType.DEBIT.toString());
        Transaction transaction =transactionService.processTransaction(transferInfo);
		assertEquals(Constant.INSUFFICENT_AMOUNT,transaction.getDescription());
		
	}

	
	@Test
	public void testbCurrentAmountWhencredit1000() throws AccountNotFoundException {
		TransactionInfo transferInfo = new TransactionInfo();
		transferInfo.setAmount(1000L);
		transferInfo.setToken(getToken());
        transferInfo.setTransactionType(TransactonType.CREDIT.toString());
        Transaction transaction =transactionService.processTransaction(transferInfo);
		assertEquals(1000L,transaction.getCurrentAmount());
		
	}
	
	@Test
	public void testaCurrentAmountWhenDebit1000() throws AccountNotFoundException {
		TransactionInfo transferInfo = new TransactionInfo();
		transferInfo.setAmount(1000L);
		transferInfo.setToken(getToken());
        transferInfo.setTransactionType(TransactonType.DEBIT.toString());
        Transaction transaction =transactionService.processTransaction(transferInfo);
		assertEquals(0L,transaction.getCurrentAmount());
		
	}
	
	

	


}
