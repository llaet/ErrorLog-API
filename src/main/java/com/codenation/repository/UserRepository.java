package com.codenation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codenation.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
