package com.codenation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codenation.model.User;
import com.codenation.service.impl.UserServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl service;
	
	@PostMapping
	@ApiOperation("create a new user object")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		if(user == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(service.save(user));
	}
	
}
