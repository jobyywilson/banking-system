package com.oracle.bank.accountservice.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.oracle.bank.accountservice.dto.TransactionInfo;
import com.oracle.bank.accountservice.dto.UserInfo;
import com.oracle.bank.accountservice.model.Account;
import com.oracle.bank.accountservice.repository.AccountRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author Joby Wilson Mathews
 *
 */
@Service
public class AccountService {
	
	@Value("${secret.key}")
	private String SECRET_KEY;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	MessageChannel output;

	public void createAccount(UserInfo userInfo) {
	
		Account account = new Account();
		account.setFirstName(userInfo.getFirstName());
		account.setLastName(userInfo.getLastName());
		account.setPhoneNumber(userInfo.getPhoneNumber());
		account.setEmail(userInfo.getEmail());
		account.setUsername(userInfo.getUsername());
		account.setPassword(userInfo.getPassword());
		accountRepository.save(account);
		
	}
	
	public UserInfo getAccountDetails(Authentication authentication) {
		Account account = accountRepository.findByUsername(authentication.getName());
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(account.getFirstName());
		userInfo.setBalance(account.getBalance());
		userInfo.setLastName(account.getLastName());
		userInfo.setPhoneNumber(account.getPhoneNumber());
		userInfo.setEmail(account.getEmail());
		userInfo.setUsername(account.getUsername());
		return userInfo;
	}
	public boolean startTransaction(TransactionInfo transactionInfo,Authentication authentication) {
		
	

		SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String token = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "SHA256").setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .compact();
        transactionInfo.setToken(token);
       
		return output.send(MessageBuilder.withPayload(transactionInfo).build());
		
		
	}
	
	

}
