package com.codenation.servico.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.codenation.enumeracao.Level;
import com.codenation.modelo.LogEvento;

//Classe de interface para criacao das regras de negocio da API
public interface LogEventoServicoInterface {

	List<LogEvento> encontrarTodos(Pageable paginavel);
	
	List<LogEvento> encontrarPorLevel(Level level, Pageable paginavel);
	
	List<LogEvento> listarColunaOrdenadaPor(String coluna, String ordem, Pageable paginavel);
	
	List<LogEvento> listarColuna(String coluna, Pageable paginavel);
	
	Optional<LogEvento> encontrarPorId(Long id);
	
	LogEvento salvar(LogEvento logEvento);
	
	void deletar(LogEvento logEvento);
	
}
