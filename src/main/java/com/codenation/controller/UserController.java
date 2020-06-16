package com.codenation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public HttpStatus save(@Valid @RequestBody User user) {
		if(user == null) {
			return HttpStatus.BAD_REQUEST;
		}
		this.service.save(user);
		return HttpStatus.CREATED;
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("delete a user object")
	public HttpStatus delete(@PathVariable Long id) {
		if(id == null) {
			return HttpStatus.BAD_REQUEST;
		}
		this.service.delete(id);
		return HttpStatus.OK;
	}
	
	@GetMapping
	@ApiOperation("return all users")
	public List<User> findAll() {
		return this.service.findAll();
	}
}
