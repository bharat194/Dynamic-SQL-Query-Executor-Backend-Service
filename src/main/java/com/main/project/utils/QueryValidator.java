package com.main.project.utils;

import java.util.Arrays;
import java.util.List;

import jakarta.xml.bind.ValidationException;

public class QueryValidator {
	
	public static void validateQuery(List<String> curColumns, String parentQuery) throws Exception{
		
		if(curColumns == null) return;
		
		if(curColumns.size() > 10) {
			throw new ValidationException("Not more than 10 columns are allowed");
		}
		List<String> presentColumn = Arrays.asList(parentQuery.substring(parentQuery.indexOf("SELECT")+7,parentQuery.indexOf("FROM")-1).split(", "));
		
		for(String curColumn:curColumns) {
			if(!presentColumn.contains(curColumn)) {
				throw new ValidationException("Column Mismatch");
			}
		}
		
	}

}
