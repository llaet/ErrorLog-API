package com.codenation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codenation.model.User;
import com.codenation.service.impl.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl service;
	
	@PostMapping
	@ApiOperation("create a new user object")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "JSON object not valid"), @ApiResponse(code = 201, message = "object created")})
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		if(user == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<User>(service.save(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/{email}")
	@ApiOperation("get a user object by email")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "user not found"), @ApiResponse(code = 200, message = "user found")})
	public ResponseEntity<UserDetails> getByEmail(@PathVariable String email) {
		return ResponseEntity.ok(this.service.loadUserByUsername(email));
	}
	
}
