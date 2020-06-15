package com.codenation.enumeration;

import java.io.Serializable;

public enum SQLOperation implements Serializable {

	LIKE("like"), 
	GREATER_THAN("gt"), 
	LESS_THAN("lt"), 
	GREATER_OR_EQUAL("gte"), 
	LESS_OR_EQUAL("lte");
	
	String pathURIValue;
	
	SQLOperation(String pathURIValue){
		this.pathURIValue = pathURIValue;
	}
	
	public String getURIValue() {
		return pathURIValue;
	}
}
