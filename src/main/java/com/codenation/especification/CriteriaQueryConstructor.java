package com.codenation.especification;

import com.codenation.enumeration.SQLOperation;

public class CriteriaQueryConstructor {

	private String column;
	private Object queryArgument;
	private SQLOperation queryOperation;
	
	public CriteriaQueryConstructor(String column, Object queryArgument, SQLOperation queryOperation) {
		super();
		this.column = column;
		this.queryArgument = queryArgument;
		this.queryOperation = queryOperation;
	}
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public Object getQueryArgument() {
		return queryArgument;
	}
	public void setQueryArgument(Object queryArgument) {
		this.queryArgument = queryArgument;
	}
	public SQLOperation getQueryOperation() {
		return queryOperation;
	}
	public void setQueryOperation(SQLOperation queryOperation) {
		this.queryOperation = queryOperation;
	}	
	
}
