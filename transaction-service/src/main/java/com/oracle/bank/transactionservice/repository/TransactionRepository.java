package com.oracle.bank.transactionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oracle.bank.transactionservice.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByUsername(String username);

}
