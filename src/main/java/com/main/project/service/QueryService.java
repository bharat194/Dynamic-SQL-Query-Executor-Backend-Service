package com.main.project.service;

import java.util.List;

import com.main.project.request.AddQueryRequest;

public interface QueryService {
	
	public String getQuery(Long id);
	
	public void addQuery(AddQueryRequest addQueryRequest);
	
	public String generateNativeQuery(String query, String curQuery, List<String> columns, String role);

}
