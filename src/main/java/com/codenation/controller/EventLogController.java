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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/log")
//HTTP requests handler class
public class EventLogController {
	
	@Autowired
	private EventLogServiceImpl service;
	
	@PostMapping
	@ApiOperation("create a new event log object")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "JSON object not valid"), @ApiResponse(code = 201, message = "object created")})
	public HttpStatus save(@Valid @RequestBody EventLog eventLog) {
		if(eventLog == null) {
			return HttpStatus.BAD_REQUEST;
		}
		this.service.save(eventLog);
		return HttpStatus.CREATED;
	}
	
	@GetMapping
	@ApiOperation("return all event log objects")
	@ApiResponses(value = @ApiResponse(code = 200, message = "list of objects found"))
	public ResponseEntity<List<EventLogDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	@ApiOperation("return a event log by id")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "event log not found"), @ApiResponse(code = 200, message = "event log found")})
	public ResponseEntity<EventLogDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findById(id));
	}
	
	@GetMapping("/{column}/{operationPrefix}-{queryArgument}")
	@ApiOperation("return a list of event log sorted by - "
			+ "$operation = like | gt (greater than) | lt (less than) "
			+ "| gte (greater than or equal) | lte (less than or equal)")
	@ApiResponses(value = @ApiResponse(code = 200, message = "list of objects found"))
	public ResponseEntity<Iterable<EventLogDTO>> orderBy(
			@PathVariable String column,
			@PathVariable String operationPrefix, 
			@PathVariable Object queryArgument, 			
			Pageable pageable) {
		return ResponseEntity.ok(service.orderBy(column, queryArgument, operationPrefix, pageable));
	}

}
