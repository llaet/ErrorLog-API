package com.codenation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codenation.dto.EventLogDTO;
import com.codenation.model.EventLog;
import com.codenation.service.impl.EventLogServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/log")
//HTTP requests handler class
public class EventLogController {
	
	@Autowired
	private EventLogServiceImpl service;
	
	@PostMapping
	@ApiOperation("create a new event log object")
	public HttpStatus save(@Valid @RequestBody EventLog eventLog) {
		if(eventLog == null) {
			return HttpStatus.BAD_REQUEST;
		}
		this.service.save(eventLog);
		return HttpStatus.CREATED;
	}
	
	@GetMapping
	@ApiOperation("return all event log objects")
	public List<EventLogDTO> encontraTodos(Pageable pageable) {
		return service.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("return a event log by id")
	public ResponseEntity<EventLogDTO> encontraPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping("/{column}/{operation}-{queryArgument}")
	@ApiOperation("return a list of event log sorted"
			+ "/column/like-queryArgument"
			+ "/column/gt-queryArgument (greater than)"
			+ "/column/lt-queryArgument (less than)"
			+ "/column/gte-queryArgument (greater than or equal)"
			+ "/column/lte-queryArgument (less than or equal)")
	public Iterable<EventLogDTO> listaColunaOrdenadaPor(
			@PathVariable String column,
			@PathVariable String operation, 
			@PathVariable Object queryArgument, 			
			Pageable pageable) {
		return this.service.orderBy(column, queryArgument, operation, pageable);
	}

}
