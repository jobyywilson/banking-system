package com.oracle.bank.accountservice.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Joby Wilson Mathews
 *
 */
public class ResponseObject {
	
	private Date timestamp;
	
	private String mensaje;
	
	private String detalles;
	
	private String httpCodeMessage;

	public ResponseObject(Date timestamp, String message, String details, String httpCodeMessage) {
		super();
		this.timestamp = timestamp;
		this.mensaje = message;
		this.detalles = details;
		this.httpCodeMessage = httpCodeMessage;
	}

	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getDetalles() {
		return detalles;
	}
	
	public static ResponseEntity<String> Ok(String message) {
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
