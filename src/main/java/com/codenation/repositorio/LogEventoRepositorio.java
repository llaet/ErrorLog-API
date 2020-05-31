package com.codenation.repositorio;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codenation.enumeracao.Level;
import com.codenation.modelo.LogEvento;

//Classe repositorio para acesso a camada de dados
@Repository
public interface LogEventoRepositorio extends JpaRepository<LogEvento, Long>{
	
	List<LogEvento> findByLevel(Level level, Pageable paginavel);
	
	@Query(value = "SELECT :coluna FROM LogEvento", nativeQuery = true)
	List<LogEvento> findBy(@Param("coluna") String coluna, Pageable paginavel);
	
	@Query(value = "SELECT :coluna FROM LogEvento ORDER BY :ordem", nativeQuery = true)
	List<LogEvento> orderBy(@Param("coluna") String coluna, @Param("ordem") String ordem, Pageable paginavel);
		
}
