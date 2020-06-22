package com.codenation.especification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.codenation.model.EventLog;

public class EventLogEspecification implements Specification<EventLog> {

	private CriteriaQueryConstructor queryConstructor;
	
	public void add(CriteriaQueryConstructor queryConstructor) {
		this.queryConstructor = queryConstructor;
	}
	
	/*
	 * @return Predicate to database query
	 * uses the queryConstructor params to create a query predicate in database
	 */
	@Override
	public Predicate toPredicate(Root<EventLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		switch(queryConstructor.getQueryOperation().toString()) {
		case "LIKE":
			return criteriaBuilder.like(root.get(queryConstructor.getColumn()), 
				"%" + queryConstructor.getQueryArgument() + "%");
		case "GREATER_THAN":
			return criteriaBuilder.greaterThan(root.get(queryConstructor.getColumn()), queryConstructor.getQueryArgument().toString());
		case "LESS_THAN":
			return criteriaBuilder.lessThan(root.get(queryConstructor.getColumn()), queryConstructor.getQueryArgument().toString());
		case "GREATER_OR_EQUAL":
			return criteriaBuilder.greaterThanOrEqualTo(root.get(queryConstructor.getColumn()), 
					queryConstructor.getQueryArgument().toString());
		case "LESS_OR_EQUAL":
			return criteriaBuilder.lessThanOrEqualTo(root.get(queryConstructor.getColumn()), 
					queryConstructor.getQueryArgument().toString());
		}
		return null;
	}
	
}
