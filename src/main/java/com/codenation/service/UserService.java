package com.codenation.service;

import java.util.List;

import com.codenation.model.User;

public interface UserService {

	void save(User user);
	void delete(Long uuid);
	List<User> findAll();
}
