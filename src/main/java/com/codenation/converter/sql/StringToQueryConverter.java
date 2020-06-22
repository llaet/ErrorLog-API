package com.codenation.converter.sql;

import java.util.Arrays;
import java.util.List;

import com.codenation.enumeration.SQLOperation;

public class StringToQueryConverter {

	private List<SQLOperation> queries;
	
	public StringToQueryConverter() {
		queries = Arrays.asList(SQLOperation.values());
	}

	public SQLOperation toSQLOperation(String query) {
		return queries.stream()
				.filter(queryOp -> queryOp.getURIValue().equals(query))
				.findFirst().get();			
	}
}
