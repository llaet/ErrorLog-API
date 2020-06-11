package com.codenation.conversor.sql;

import java.util.Arrays;
import java.util.List;

import com.codenation.enumeracao.OperacaoSQL;

public class StringParaEnumOpSQL {
	
	private List<OperacaoSQL> queries;
	
	public StringParaEnumOpSQL() {
		queries = Arrays.asList(OperacaoSQL.values());
	}

	public OperacaoSQL converteOperacaoSQL(String query) {
		return queries.stream()
				.filter(operacaoSQL -> operacaoSQL.getValorURI().equals(query))
				.findFirst().get();			
	}
}
