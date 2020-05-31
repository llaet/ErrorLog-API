package com.codenation.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codenation.enumeracao.Level;
import com.codenation.modelo.LogEvento;
import com.codenation.repositorio.LogEventoRepositorio;
import com.codenation.servico.interfaces.LogEventoServicoInterface;

//Classe de servico para implementacao das regras de negocio da API
@Service
public class LogEventoServico implements LogEventoServicoInterface {

	@Autowired
	private LogEventoRepositorio repositorio;

	/*
	 * salva um novo objeto LogEvento
	 */
	@Override
	public LogEvento salvar(LogEvento logEvento) {
		return this.repositorio.save(logEvento);
	}

	/*
	 * deleta um objeto LogEvento
	 */
	@Override
	public void deletar(LogEvento logEvento) {
		this.repositorio.delete(logEvento);
	}
	
	/*
	 * @return List<LogEvento> 
	 * lista todos os logs
	 */
	@Override
	public List<LogEvento> encontrarTodos(Pageable pageable) {
		return this.repositorio.findAll();
	}
	
	/*
	 * @return Optional<LogEvento> 
	 * retorna um objeto LogEvento conforme o ID informado
	 */
	@Override
	public Optional<LogEvento> encontrarPorId(Long id) {
		return this.repositorio.findById(id);
	}

	/*
	 * @return List<LogEvento> 
	 * lista todos os logs por determinado level (error, warning, info)
	 */
	@Override
	public List<LogEvento> encontrarPorLevel(Level level, Pageable paginavel) {
		return this.repositorio.findByLevel(level, paginavel);
	}

	/*
	 * @return List<LogEvento> 
	 * lista todos os logs por determinadas colunas e ordenacao
	 */
	@Override
	public List<LogEvento> listarColunaOrdenadaPor(String coluna, String ordem, Pageable paginavel) {
		return this.repositorio.orderBy(coluna, ordem, paginavel);
	}
	
	/*
	 * @return List<LogEvento> 
	 * lista todos os logs por determinada coluna
	 */
	@Override
	public List<LogEvento> listarColuna(String coluna, Pageable paginavel) {
		return this.repositorio.findBy(coluna, paginavel);
	}
}
