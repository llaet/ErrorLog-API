package com.codenation.converter.mapper;

import com.codenation.dto.EventLogDTO;
import com.codenation.model.EventLog;

//map an object to DTO
public class EventLogMapper {

	public EventLogMapper() {}
	
	/*
	 * @return EventLogDTO object
	 * map a eventLog object to DTO
	 */
	public EventLogDTO toDTO(EventLog eventLog) {
		EventLogDTO eventLogDTO = new EventLogDTO (
				eventLog.getId(),
				eventLog.getLevel(),
				eventLog.getEventDescription(),
				eventLog.getOrigin(),
				eventLog.getDate(),
				eventLog.getQuantity());
		return eventLogDTO;		
	}
}
