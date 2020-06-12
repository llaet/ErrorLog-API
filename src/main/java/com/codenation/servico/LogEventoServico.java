package com.codenation.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codenation.conversor.sql.StringParaEnumOpSQL;
import com.codenation.enumeracao.OperacaoSQL;
import com.codenation.especificacao.CriteriaQueryConstrutor;
import com.codenation.especificacao.LogEventoEspecificacao;
import com.codenation.modelo.LogEvento;
import com.codenation.repositorio.LogEventoRepositorio;
import com.codenation.servico.interfaces.LogEventoServicoInterface;

//Classe de servico para implementacao das regras de negocio da API
@Service
public class LogEventoServico implements LogEventoServicoInterface {

	@Autowired
	private LogEventoRepositorio repositorio;
	@Autowired(required = false)
	private LogEventoEspecificacao especificacao;
	
	public LogEventoServico() {
		especificacao = new LogEventoEspecificacao();
	}

	/*
	 * salva um novo objeto LogEvento
	 */
	@Override
	public LogEvento salva(LogEvento logEvento) {
		return this.repositorio.save(logEvento);
	}
	
	/*
	 * @return List<LogEvento> 
	 * lista todos os logs
	 */
	@Override
	public List<LogEvento> encontraTodos(Pageable paginavel) {
		return this.repositorio.findAll(paginavel).getContent();
	}
	
	/*
	 * @return Optional<LogEvento> 
	 * retorna um objeto LogEvento conforme o ID informado
	 */
	@Override
	public Optional<LogEvento> encontraPorId(Long id) {
		return this.repositorio.findById(id);
	}
	
	/*
	 * @return List<LogEvento> 
	 * lista todos os logs por determinadas colunas e ordenacao
	 */
	@Override
	public List<LogEvento> listaColunaOrdenadaPor(String coluna, Object argumentoQuery, String tipoOrdenacao, Pageable paginavel) {
		OperacaoSQL query = new StringParaEnumOpSQL().converteOperacaoSQL(tipoOrdenacao);
		especificacao.adiciona(new CriteriaQueryConstrutor(coluna,argumentoQuery,query));
		return this.repositorio.findAll(especificacao,paginavel).getContent();
	}
	
}
