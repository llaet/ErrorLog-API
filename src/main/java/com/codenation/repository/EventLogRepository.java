package com.codenation.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.codenation.enumeration.Level;
import com.codenation.model.EventLog;

@Repository
//Interface that does the access to database layer
public interface EventLogRepository extends JpaRepository<EventLog, Long>, JpaSpecificationExecutor<EventLog> {
	
	List<EventLog> findByLevel(Level level, Pageable pageable);
}

