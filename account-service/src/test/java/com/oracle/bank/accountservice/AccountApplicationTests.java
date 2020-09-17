package com.oracle.bank.accountservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.bank.accountservice.controller.AccountController;
import com.oracle.bank.accountservice.dto.TransactionInfo;
import com.oracle.bank.accountservice.dto.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@FixMethodOrder(MethodSorters.DEFAULT)
@AutoConfigureMockMvc
@WebAppConfiguration
class AccountApplicationTests {
	
	@Autowired
	AccountController accountController;
	
	static  Validator validator;
	UserInfo userInfo;
	
	 @Autowired
	 private WebApplicationContext context;
	 MockMvc mvc;

	
	@BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
    }
	
	@BeforeEach
    public void initiate() {
		userInfo = getUserInfo();
		mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        
    }
	
	public UserInfo getUserInfo() {
		
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("John");
		userInfo.setLastName("Smith");
		userInfo.setEmail("John.SMith@gmail.com");
		userInfo.setPhoneNumber("9876543210");
		userInfo.setUsername("johnsmith0");
		userInfo.setPassword("johnsmith0");
		return userInfo;
		
	}
	
	@Test
	void testSuccessLogin() throws Exception {
		RequestBuilder requestBuilder = formLogin().user("johnsmith").password("johnsmith");
		mvc.perform(requestBuilder)
		    .andDo(print())
		    .andExpect(redirectedUrl("/home"));
	}
	
	@Test
	void testFailureLogin() throws Exception {
		RequestBuilder requestBuilder = formLogin().user("johnsmith0").password("johnsmit");
		mvc.perform(requestBuilder)
		    .andDo(print())
		    .andExpect(redirectedUrl("/login?error"));
		
	}
	
	@Test
	void testTransaction() throws Exception {
		mvc.perform(post("/account/startTransaction")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(new TransactionInfo())))
	            .andExpect(status().is3xxRedirection());
		
	}


	@Test
	void createSuccessAccount() {
		ResponseEntity<String> response = accountController.createAccount(userInfo);
		assertEquals(response.getBody(), "johnsmith0");
	}
	
	@Test
	void validateEmptyPassword() {
		userInfo.setPassword(null);
		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	void validateEmptyUsername() {
		userInfo.setUsername(null);
		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	void validateEmptyFirstName() {
		userInfo.setFirstName(null);
		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	void validateEmptyLastName() {
		userInfo.setLastName(null);
		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	void validateEmptyPhoneNumber() {
		userInfo.setPhoneNumber(null);
		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	void validateEmptyEmail() {
		userInfo.setEmail(null);
		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	void validateIncorrectEmail() {
		userInfo.setEmail("ashsa");
		Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
		assertFalse(violations.isEmpty());
	}
	

	
	

}
