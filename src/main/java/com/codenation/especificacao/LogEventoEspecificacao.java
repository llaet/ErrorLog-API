package com.codenation.especificacao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.codenation.modelo.LogEvento;

public class LogEventoEspecificacao implements Specification<LogEvento> {

	private CriteriaQueryConstrutor construtorQuery;
	
	public void adiciona(CriteriaQueryConstrutor construtorQuery) {
		this.construtorQuery = construtorQuery;
	}
	
	@Override
	public Predicate toPredicate(Root<LogEvento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		switch(construtorQuery.getOperacao().toString()) {
		case "IGUAL":
			return criteriaBuilder.like(root.get(construtorQuery.getColunaTabela()), 
				"%" + construtorQuery.getArgumentoDaQuery().toString().toLowerCase() + "%");
		case "MAIOR_QUE":
			return criteriaBuilder.greaterThan(root.get(construtorQuery.getColunaTabela()), construtorQuery.getArgumentoDaQuery().toString());
		case "MENOR_QUE":
			return criteriaBuilder.lessThan(root.get(construtorQuery.getColunaTabela()), construtorQuery.getArgumentoDaQuery().toString());
		case "MAIOR_OU_IGUAL":
			return criteriaBuilder.greaterThanOrEqualTo(root.get(construtorQuery.getColunaTabela()), 
					construtorQuery.getArgumentoDaQuery().toString());
		case "MENOR_OU_IGUAL":
			return criteriaBuilder.lessThanOrEqualTo(root.get(construtorQuery.getColunaTabela()), 
					construtorQuery.getArgumentoDaQuery().toString());
		}
		return null;
	}

}
