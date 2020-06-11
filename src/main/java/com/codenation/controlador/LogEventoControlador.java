package com.codenation.controlador;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codenation.modelo.LogEvento;
import com.codenation.servico.LogEventoServico;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/log")
//classe controladora da REST API
public class LogEventoControlador {

	@Autowired
	private LogEventoServico logEventoServico;
	
	@PostMapping
	@ApiOperation("Cria um novo objeto LogEvento")
	public ResponseEntity<LogEvento> cria(@Valid @RequestBody LogEvento logEvento) {
		if(logEvento == null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<LogEvento>(this.logEventoServico.salva(logEvento), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Deleta um objeto LogEvento")
	public HttpStatus deleta(@PathVariable Long id) {
		if(id == null | id < 0) {
			return HttpStatus.BAD_REQUEST;
		}
		this.logEventoServico.deleta(id);
		return HttpStatus.OK;
	}
	
	@GetMapping
	@ApiOperation("retorna todos os log's de eventos registrados")
	public Iterable<LogEvento> encontraTodos(Pageable paginavel) {
		return this.logEventoServico.encontraTodos(paginavel);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("retorna o log de evento conforme Id informado")
	public Optional<LogEvento> encontraPorId(@PathVariable Long id) {
		return this.logEventoServico.encontraPorId(id);
	}
	
	@GetMapping("/{coluna}/{tipoOrdenacao}-{argumentoQuery}")
	@ApiOperation("retorna uma lista com objetos LogEvento filtrados/ordenados conforme a busca do usuario"
			+ "/coluna/like-argumento"
			+ "/coluna/gt-argumento (greater than)"
			+ "/coluna/lt-argumento (less than)"
			+ "/coluna/gte-argumento (greater than or equal)"
			+ "/coluna/lte-argumento (less than or equal)")
	public Iterable<LogEvento> listaColunaOrdenadaPor(
			@PathVariable String coluna, 
			@PathVariable Object argumentoQuery, 
			@PathVariable String tipoOrdenacao, 
			Pageable paginavel) {
		return this.logEventoServico.listaColunaOrdenadaPor(coluna, argumentoQuery, tipoOrdenacao, paginavel);
	}
	
}
