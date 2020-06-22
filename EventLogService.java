package com.codenation.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.codenation.dto.EventLogDTO;
import com.codenation.enumeration.Level;
import com.codenation.model.EventLog;

//interface for event log methods rules
public interface EventLogService {

	List<EventLogDTO> findAll(Pageable pageable);
	
	List<EventLogDTO> findByLevel(Level level, Pageable pageable);
	
	List<EventLogDTO> orderBy(String column, Object queryArgument, String sortedBy, Pageable pageable);
	
	EventLogDTO findById(Long id);
	
	void save(EventLog eventLog);
}
