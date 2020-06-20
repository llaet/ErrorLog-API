package com.codenation.controller.advice;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorMessageConstructor {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;
	private String message;
	
	public ErrorMessageConstructor() {}
	
	public ErrorMessageConstructor(LocalDateTime date, String message) {
		super();
		this.date = date;
		this.message = message;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
