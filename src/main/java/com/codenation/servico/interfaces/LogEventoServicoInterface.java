package com.codenation.servico.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.codenation.modelo.LogEvento;

//Classe de interface para criacao das regras de negocio da API
public interface LogEventoServicoInterface {

	List<LogEvento> encontraTodos(Pageable paginavel);
	
	List<LogEvento> listaColunaOrdenadaPor(String coluna, Object argumentoQuery, String tipoOrdenacao, Pageable paginavel);
	
	Optional<LogEvento> encontraPorId(Long id);
	
	LogEvento salva(LogEvento logEvento);

}
