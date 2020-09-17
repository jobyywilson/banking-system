package com.oracle.bank.accountservice.config;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
	

@ControllerAdvice
public class AccountGlobalExpHandler  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ResponseObject> handleSQLException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
		ResponseObject obj = new  ResponseObject(new Date(), "Username already exist Exception", ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
		ResponseEntity<ResponseObject> rspEntity= new ResponseEntity<ResponseObject>(obj,HttpStatus.BAD_REQUEST);
		return  rspEntity;
		
	}
	
	@ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
	public ResponseEntity<ResponseObject> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
		ResponseObject obj = new  ResponseObject(new Date(), "Username already exist", ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
		ResponseEntity<ResponseObject> rspEntity= new ResponseEntity<ResponseObject>(obj,HttpStatus.BAD_REQUEST);
		return  rspEntity;
		
	}

}
