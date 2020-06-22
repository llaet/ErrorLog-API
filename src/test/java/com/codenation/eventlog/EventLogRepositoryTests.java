package com.codenation.eventlog;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.codenation.enumeration.Level;
import com.codenation.model.EventLog;
import com.codenation.repository.EventLogRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventLogRepositoryTests {

	@Autowired
	private TestEntityManager testEntity;
	
	@Autowired
	private EventLogRepository repository;
	
	@Test
	public void whenFindAll() {
		//given
		EventLog log = new EventLog(null,Level.ERROR, 
				"conexão com aws falhando", "falha de conexão", "215.123.12.5", LocalDateTime.now(), 6L);
		testEntity.persist(log);
		testEntity.flush();
		
		//when
		List<EventLog> foundLogs = this.repository.findAll();
		
		//then
		assertThat(foundLogs.contains(log));		
	}
	
	@Test
	public void whenFindById() {
		//given
		EventLog log = new EventLog(null,Level.ERROR, 
				"conexão com aws falhando", "falha de conexão", "215.123.12.5", LocalDateTime.now(), 6L);
		testEntity.persist(log);
		testEntity.flush();
		
		//when
		EventLog foundLog = this.repository.findById(log.getId()).get();
		
		//then
		assertThat(foundLog.getId()).isEqualTo(log.getId());		
	}
}
