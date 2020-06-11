package com.codenation.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.codenation.modelo.LogEvento;

//Classe repositorio para acesso a camada de dados
@Repository
public interface LogEventoRepositorio extends JpaRepository<LogEvento, Long>, JpaSpecificationExecutor<LogEvento> {
}
