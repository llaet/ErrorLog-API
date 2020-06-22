package com.codenation.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.codenation.enumeration.Level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "logs")
@EntityListeners(AuditingEntityListener.class)
//model
public class EventLog {
		
		@Id
		@Column
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private Long id;

		@Column(nullable = false)
		@Enumerated(EnumType.STRING)
		private Level level;

		@Column(name = "event_description")
		private String eventDescription;
		
		@NotBlank
		@Column(name = "event_log",nullable = false)
		private String eventLog;
		
		@Column
		private String origin;
		
		@CreatedDate
		@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
		private LocalDateTime createdAt;	

		@Column
		@PositiveOrZero
		private Long quantity;

		public EventLog() {}
		
		public EventLog(Long id, Level level, String eventDescription, @NotBlank String eventLog, String origin,
				LocalDateTime createdAt, @PositiveOrZero Long quantity) {
			super();
			this.id = id;
			this.level = level;
			this.eventDescription = eventDescription;
			this.eventLog = eventLog;
			this.origin = origin;
			this.createdAt = createdAt;
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

		public String getEventLog() {
			return eventLog;
		}

		public void setEventLog(String eventLog) {
			this.eventLog = eventLog;
		}

		public String getOrigin() {
			return origin;
		}

		public void setOrigin(String origin) {
			this.origin = origin;
		}

		public LocalDateTime getDate() {
			return createdAt;
		}

		public void setDate(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public Long getQuantity() {
			return quantity;
		}

		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}
		
		
}
