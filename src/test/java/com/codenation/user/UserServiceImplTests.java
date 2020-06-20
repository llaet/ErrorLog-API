package com.codenation.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.codenation.model.User;
import com.codenation.repository.UserRepository;
import com.codenation.service.impl.UserServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {

	@InjectMocks
	private UserServiceImpl service;
	@Mock
	private UserRepository repository;
	
	@Test
	public void whenFindbyEmail() {
		String email = "test@test.com";
		User user = mock(User.class);
		
		when(user.getUsername()).thenReturn(email);
		when(this.repository.findByEmail(email)).thenReturn(user);
		
		//assertion
		assertEquals(email, this.service.loadUserByUsername(email).getUsername());
	}
}
