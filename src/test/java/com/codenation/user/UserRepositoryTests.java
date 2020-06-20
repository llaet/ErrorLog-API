package com.codenation.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.codenation.model.User;
import com.codenation.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

	@Autowired
	private TestEntityManager testEntity;
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void whenFindAll() {
		//given
		User user = new User(null, "test@test.com", "12345", LocalDateTime.now());
		testEntity.persist(user);
		testEntity.flush();
		
		//when
		List<User> users = this.repository.findAll();
		
		//then
		assertThat(users.contains(user));		
	}
	
	@Test
	public void whenFindByEmail() {
		//given
		User user = new User(null, "test@test.com", "12345", LocalDateTime.now());
		testEntity.persist(user);
		testEntity.flush();
		
		//when
		User returnedUser = this.repository.findByEmail(user.getEmail());
		
		//then
		assertThat(returnedUser.getEmail()).isEqualTo(user.getEmail());		
	}
}
