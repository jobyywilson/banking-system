package com.oracle.bank.accountservice.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oracle.bank.accountservice.model.Account;
import com.oracle.bank.accountservice.repository.AccountRepository;

/**
 * @author Joby Wilson Mathews
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String password = request.getParameter("password");
        
        Account account = accountRepository.findByUsernameAndPassword(username,password);

        UserBuilder builder = null;
        if (account != null) {
          builder = org.springframework.security.core.userdetails.User.withUsername(username);
          builder.password(new BCryptPasswordEncoder().encode(account.getPassword()));
          builder.roles(new String[] {"USER"});
        } else {
          throw new UsernameNotFoundException("Account not found.");
        }

        return builder.build();
	}

}
