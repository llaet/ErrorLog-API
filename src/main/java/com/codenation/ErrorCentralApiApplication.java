package com.codenation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ErrorCentralApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorCentralApiApplication.class, args);
	}

}
