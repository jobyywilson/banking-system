
package com.oracle.bank.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oracle.bank.transactionservice.model.Account;

/**
 * @author Joby Wilson Mathews
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByUsernameAndPassword(String username,String password);
	Account findByUsername(String username);

}
