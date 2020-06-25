package com.codenation.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.codenation.repository.UserRepository;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private UserRepository repository;
	
	@TestConfiguration
	static class config{
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication("admin", "admin123");
		}
	}
	
	@Test
	public void whenGetByEmailWithIncorretEmail() {
		//given
		restTemplate = this.restTemplate.withBasicAuth("test", "test123");
		
		//when
		ResponseEntity<String> response = this.restTemplate.getForEntity("/user/none@none.com", String.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);		
	}
	
	@Test
	public void whenPostUserWithUsernameAndPasswordMissing() {
		//given
		restTemplate = this.restTemplate.withBasicAuth("test", "test123");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		//when
		ResponseEntity<String> response = this.restTemplate.postForEntity("/user", entity, String.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);		
	}
	
	@Test
	public void whenPostUserWithValidUsernameAndPassword() throws JSONException {
		//given
		restTemplate = this.restTemplate.withBasicAuth("test", "test123");
		JSONObject json = new JSONObject();
		json.put("email", "testing@test.com");
		json.put("passwordKey", "12345");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
		
		//when
		ResponseEntity<String> response = this.restTemplate.postForEntity("/user", entity, String.class);
		
		//then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);		
	}
	
}
