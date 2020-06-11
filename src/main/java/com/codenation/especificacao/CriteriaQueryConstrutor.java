package com.codenation.especificacao;

import com.codenation.enumeracao.OperacaoSQL;

public class CriteriaQueryConstrutor {

	private String colunaTabela;
	private Object argumentoDaQuery;
	private OperacaoSQL operacao;
	
	public CriteriaQueryConstrutor() {}
	
	public CriteriaQueryConstrutor(String colunaTabela, Object argumentoDaQuery, OperacaoSQL operacao) {
		this.colunaTabela = colunaTabela;
		this.argumentoDaQuery = argumentoDaQuery;
		this.operacao = operacao;
	}
	
	public String getColunaTabela() {
		return colunaTabela;
	}

	public void setColunaTabela(String colunaTabela) {
		this.colunaTabela = colunaTabela;
	}

	public Object getArgumentoDaQuery() {
		return argumentoDaQuery;
	}

	public void setArgumentoDaQuery(String argumentoDaQuery) {
		this.argumentoDaQuery = argumentoDaQuery;
	}

	public OperacaoSQL getOperacao() {
		return operacao;
	}

	public void setOperacao(OperacaoSQL operacao) {
		this.operacao = operacao;
	}
}
