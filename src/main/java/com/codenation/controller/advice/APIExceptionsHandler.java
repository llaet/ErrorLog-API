package com.codenation.controller.advice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.hibernate.PropertyValueException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
//handle the exceptions of API and return a more specified info
public class APIExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {PropertyValueException.class})
	public ResponseEntity<Object> handlePropertyValueException(PropertyValueException ex) {
		String exceptionMessage = ex.getLocalizedMessage().concat(
				ex.getPropertyName());
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {NoSuchElementException.class})
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
		String exceptionMessage = ex.getLocalizedMessage();
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
		String exceptionMessage = ex.getLocalizedMessage();
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		String exceptionMessage = ex.getMostSpecificCause().toString();
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		String exceptionMessage = ex.getLocalizedMessage().concat(
				ex.getConstraintViolations().toString());
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {RequestRejectedException.class})
	public ResponseEntity<Object> handleRequestRejectedException(RequestRejectedException ex) {
		String exceptionMessage = "The URL path doesn't exist";
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {PropertyReferenceException.class})
	public ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException ex) {
		String exceptionMessage = ex.getLocalizedMessage();
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {InvalidRequestException.class})
	public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex) {
		String exceptionMessage = ex.getLocalizedMessage();
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {InternalAuthenticationServiceException.class})
	public ResponseEntity<Object> handleInternalAuthenticationServiceException(
			InternalAuthenticationServiceException ex) {
		String exceptionMessage = ex.getLocalizedMessage();
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		String exceptionMessage = ex.getLocalizedMessage();
		return new ResponseEntity<>(buildErrorMessage(exceptionMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	private ErrorMessageConstructor buildErrorMessage(String ex) {
		//construct and return ErrorMessageConstructor object
		return new ErrorMessageConstructor(LocalDateTime.now(), ex);
	}
	
}
