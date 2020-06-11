package com.codenation.enumeracao;

public enum OperacaoSQL {

	IGUAL("like"), 
	MAIOR_QUE("gt"), 
	MENOR_QUE("lt"), 
	MAIOR_OU_IGUAL("gte"), 
	MENOR_OU_IGUAL("lte");
	
	String valorURI;
	
	OperacaoSQL(String valorURI){
		this.valorURI = valorURI;
	}
	
	public String getValorURI() {
		return valorURI;
	}
}
