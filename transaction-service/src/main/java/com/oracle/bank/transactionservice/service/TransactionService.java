package com.oracle.bank.transactionservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import com.oracle.bank.transactionservice.dto.TransactionInfo;
import com.oracle.bank.transactionservice.model.Account;
import com.oracle.bank.transactionservice.model.Transaction;
import com.oracle.bank.transactionservice.repository.AccountRepository;
import com.oracle.bank.transactionservice.repository.TransactionRepository;
import com.oracle.bank.transactionservice.enums.Constant;
import com.oracle.bank.transactionservice.enums.Status;
import com.oracle.bank.transactionservice.enums.TransactonType;
import com.oracle.bank.transactionservice.exception.AccountNotFoundException;

import io.jsonwebtoken.Jwts;

/**
 * @author Joby Wilson Mathews
 *
 */
@Service
public class TransactionService {
	Logger logger = LoggerFactory.getLogger(TransactionService.class);
	
	@Value("${secret.key}")
	private String SECRET_KEY;
	
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@StreamListener(target = Sink.INPUT)
	public void consumeTransaction(TransactionInfo transactionInfo) throws AccountNotFoundException {
		processTransaction(transactionInfo);
	}
	public Transaction processTransaction(TransactionInfo transactionInfo) throws AccountNotFoundException {
		
		Transaction transaction = new Transaction();
		
		transaction.setTransactionType(transactionInfo.getTransactionType());
		transaction.setAmount(transactionInfo.getAmount());
		logger.info("Initated transaction");
		String username=Jwts.parser()
        .setSigningKey(SECRET_KEY.getBytes())
        .parseClaimsJws(transactionInfo.getToken()).getBody().getSubject();
		
		transaction.setUsername(username);

		Account account = accountRepository.findByUsername(username);
		if(account==null) {
			logger.error("Account not found for "+transactionInfo.getUsername());
			throw new AccountNotFoundException("Account not found for "+transactionInfo.getUsername());
		}
		transaction.setCurrentAmount(account.getBalance());
		Long currentBalance = account.getBalance();
		if(transactionInfo.getTransactionType().equals(TransactonType.CREDIT.toString())) {
			Long newBalance = currentBalance+transaction.getAmount();
			account.setBalance(newBalance);
			transaction.setCurrentAmount(newBalance);
			transaction.setStatus(Status.SUCCESS.toString());
			transaction.setDescription(Constant.CREDIT_SUCCESSFULLY_DONE);
		}else if(transactionInfo.getTransactionType().equals(TransactonType.DEBIT.toString())) {
			Long newBalance = currentBalance-transaction.getAmount();
			if(newBalance<0) {
				transaction.setStatus(Status.FAIL.toString());
				transaction.setDescription(Constant.INSUFFICENT_AMOUNT);
				logger.error(Constant.INSUFFICENT_AMOUNT+username);
			}else {
				transaction.setCurrentAmount(newBalance);
				account.setBalance(currentBalance +transaction.getAmount());
				transaction.setStatus(Status.SUCCESS.toString());
				transaction.setDescription(Constant.DEBIT_SUCCESSFULLY_DONE);
			}
		}else {
			transaction.setStatus(Status.FAIL.toString());
			transaction.setDescription(Constant.INVALID_TRANSACTION);
		}
		accountRepository.save(account);
		transaction =transactionRepository.save(transaction);
		logger.info("Transaction done successfully");
		return transaction;
	}

	public List<Transaction> getAllTransactionDetails(String username) {

		return transactionRepository.findByUsername(username);

	}
}
