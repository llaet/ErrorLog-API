package com.codenation.controller.advice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.hibernate.PropertyValueException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
//handle the exceptions of API and return a more especified info
public class APIExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {PropertyValueException.class})
	public ResponseEntity<Object> handlePropertyValueException(PropertyValueException ex) {				
		return new ResponseEntity<>(buildErrorMessage(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {NoSuchElementException.class})
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {			
		return new ResponseEntity<>(buildErrorMessage(ex), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {			
		return new ResponseEntity<>(buildErrorMessage(ex), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	private ErrorMessageConstructor buildErrorMessage(Exception ex) {
		String errorMessage = ex.getLocalizedMessage();		
		if(errorMessage == null) {
			errorMessage = ex.toString();
		}
		//construct and return ErrorMessageConstructor object
		return new ErrorMessageConstructor(LocalDateTime.now(), errorMessage);
	}
	
}
