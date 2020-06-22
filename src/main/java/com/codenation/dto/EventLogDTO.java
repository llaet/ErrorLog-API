package com.codenation.dto;

import java.time.LocalDateTime;

import com.codenation.enumeration.Level;
import com.fasterxml.jackson.annotation.JsonFormat;

//data transfer object for being returned by controller
public class EventLogDTO {

	private Long id;
	private Level level;
	private String eventDescription;
	private String origin;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;	
	private Long quantity;
	
	public EventLogDTO(Long id, Level level, String eventDescription, String origin,
			LocalDateTime date, Long quantity) {
		super();
		this.id = id;
		this.level = level;
		this.eventDescription = eventDescription;
		this.origin = origin;
		this.date = date;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
}
