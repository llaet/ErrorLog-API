package com.codenation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codenation.model.User;
import com.codenation.repository.UserRepository;
import com.codenation.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public void save(User user) {
		this.repository.saveAndFlush(user);
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);;		
	}

	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

}
