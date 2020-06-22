package com.codenation.eventlog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.codenation.repository.EventLogRepository;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventLogControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private EventLogRepository repository;
	
	@TestConfiguration
	static class config{
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication("admin", "admin123");
		}
	}
	
	@Test
	public void whenGetEventLogWithIncorretUsernameAndPassword() {
		//given
		restTemplate = this.restTemplate.withBasicAuth("test", "test123");
		
		//when
		ResponseEntity<String> response = this.restTemplate.getForEntity("/log", String.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);		
	}
	
	@Test
	public void whenGetEventLogByIdWithIncorretUsernameAndPassword() {
		//given
		restTemplate = this.restTemplate.withBasicAuth("test", "test123");
		
		//when
		ResponseEntity<String> response = this.restTemplate.getForEntity("/log/1", String.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);		
	}
	
	@Test
	public void whenGetEventLogByLevelWithIncorretUsernameAndPassword() {
		//given
		restTemplate = this.restTemplate.withBasicAuth("test", "test123");
		
		//when
		ResponseEntity<String> response = this.restTemplate.getForEntity("/log/ERROR", String.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);		
	}
	
	@Test
	public void whenGetEventLogSortedWithIncorretUsernameAndPassword() {
		//given
		restTemplate = this.restTemplate.withBasicAuth("test", "test123");
		
		//when
		ResponseEntity<String> response = this.restTemplate.getForEntity("/log/quantity/gt-2", String.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);		
	}	
	
}
