package com.codenation.eventlog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.codenation.model.EventLog;
import com.codenation.repository.EventLogRepository;
import com.codenation.service.impl.EventLogServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EventLogServiceImplTests {
	
	@InjectMocks
	private EventLogServiceImpl service;
	@Mock
	private EventLogRepository repository;
	
	@Test
	public void whenFindbyId() {
		Long id = 1L;
		EventLog log = mock(EventLog.class);
		
		when(log.getId()).thenReturn(id);
		when(this.repository.findById(id).get()).thenReturn(log);
		
		//assertion
		assertEquals(id, this.service.findById(id).getId());
	}
}
