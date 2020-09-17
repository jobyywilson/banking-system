package com.oracle.bank.accountservice.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Joby Wilson Mathews
 *
 */
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

	 @Bean
	  public UserDetailsService userDetailsService() {
	    return new UserDetailsServiceImpl();
	  };
	  
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	  
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	  }
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    .antMatchers("/account/create","/actuator/**").permitAll()
	    .anyRequest().hasAnyRole("USER")
	    .and()
	    .formLogin()
	    .defaultSuccessUrl("/home", true)
	    .and()
	    .logout().permitAll().logoutSuccessUrl("/login")
	    .and()
	    .csrf().disable();
	  }


	
	  
}
