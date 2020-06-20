package com.codenation.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codenation.converter.mapper.EventLogMapper;
import com.codenation.converter.sql.StringToQueryConverter;
import com.codenation.dto.EventLogDTO;
import com.codenation.enumeration.SQLOperation;
import com.codenation.especification.CriteriaQueryConstructor;
import com.codenation.especification.EventLogEspecification;
import com.codenation.model.EventLog;
import com.codenation.repository.EventLogRepository;
import com.codenation.service.EventLogService;

@Service
//implements the event log's methods of API
public class EventLogServiceImpl implements EventLogService{
	
	@Autowired
	private EventLogRepository repository;
	@Autowired(required = false)
	private EventLogMapper mapper;
	@Autowired(required = false)
	private EventLogEspecification especification;
	
	EventLogServiceImpl(){
		mapper = new EventLogMapper();
		especification = new EventLogEspecification();
	}

	/*
	 * @return List<EventLogDTO>
	 * return a list of event log mapped to DTO
	 */
	@Override
	public List<EventLogDTO> findAll(Pageable pageable) {
		List<EventLog> eventLog = this.repository.findAll(pageable).getContent();
		return eventLog.stream().map(log -> mapper.toDTO(log)).collect(Collectors.toList());
	}

	/*
	 * @return List<EventLogDTO>
	 * create a new query with column and operation param's and return it mapped to DTO
	 */
	@Override
	public List<EventLogDTO> orderBy(String column, Object queryArgument, String sortedBy, Pageable pageable) {
		//parse a String to SQL Operation object
		SQLOperation operation = new StringToQueryConverter().toSQLOperation(sortedBy);
		//create a specification for a new sql query
		this.especification.add(new CriteriaQueryConstructor(column, queryArgument, operation));
		List<EventLog> eventLog = repository.findAll(especification,pageable).getContent();
		//map the objects to DTO and return them
		return eventLog.stream().map(log -> mapper.toDTO(log)).collect(Collectors.toList());
	}

	/*
	 * @return EventLog object
	 * find event log by id param and return it mapped to DTO
	 */
	@Override
	public EventLogDTO findById(Long id) {
		EventLog eventLog = repository.findById(id).get();
		return this.mapper.toDTO(eventLog);
	}

	/*
	 * create and save a new event log
	 */
	@Override
	public void save(EventLog eventLog) {
		this.repository.saveAndFlush(eventLog);
	}


}
