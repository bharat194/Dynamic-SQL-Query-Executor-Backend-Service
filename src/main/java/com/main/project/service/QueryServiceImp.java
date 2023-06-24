package com.main.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.main.project.dataBase.QuerydB;
import com.main.project.repo.QueryRepo;
import com.main.project.request.AddQueryRequest;
import com.main.project.utils.Constants;

@Service
public class QueryServiceImp implements QueryService{
	
	private QueryRepo queryRepo;
	
	public QueryServiceImp(QueryRepo queryRepo) {
		super();
		this.queryRepo = queryRepo;
	}
	
	@Override
	public String getQuery(Long id) {
		
		return queryRepo.findById(id).get().getQuery();
		
	}

	@Override
	public void addQuery(AddQueryRequest addQueryRequest) {
		
		addQueryRequest.getQueryList().forEach(curQuery -> {
			
	 		QuerydB querydB = QuerydB.builder().
	 				query(curQuery).
	 				build();
	 		
			queryRepo.save(querydB);
			
		});
	}

	@Override
	public String generateNativeQuery(String newQuery,String curQuery, List<String> columns, String role) {
		
		if(columns == null) columns = role.equals("Agent")?new ArrayList<>(Constants.ALLOWEED_COLUMNS_FOR_AGENT):new ArrayList<>(Constants.ALLOWEED_COLUMNS_FOR_Admin);
		
		if(role.equals("Agent")) {
			List<String> newColumns = new ArrayList<>();
			for(String curColumn:columns) {
				if(Constants.ALLOWEED_COLUMNS_FOR_AGENT.contains(curColumn)) 
					newColumns.add(curColumn);
			}
			columns = new ArrayList<>(newColumns);
		}
		
		newQuery = newQuery.replace("$requiredColumns$", String.join(",", columns));

		newQuery = newQuery.replace("$dbQuery$",curQuery);
		
		return newQuery;
		
	}
	
}
