package com.codenation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codenation.model.User;
import com.codenation.repository.UserRepository;
import com.codenation.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public User save(User user) {
		return this.repository.saveAndFlush(user);
	}

	/*
	 * @return UserDetails
	 * return a UserDetails object found by email param
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.repository.findByEmail(username);
	}

}
