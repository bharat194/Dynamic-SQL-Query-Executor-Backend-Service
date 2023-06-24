package com.main.project.query;

import java.util.HashMap;
import java.util.List;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import com.main.project.request.ExecuteQueryRequest;
import com.main.project.response.ResponseOutput;
import com.main.project.service.QueryService;
import com.main.project.utils.Constants;
import com.main.project.utils.QueryValidator;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.ValidationException;

@Component
@SuppressWarnings({"unchecked","deprecation"})
public class QueryHandler {
	
	private EntityManager entityManager;
	
	private QueryService queryService;
	
	public QueryHandler(EntityManager entityManager, QueryService queryService) {
		super();
		this.entityManager = entityManager;	
		this.queryService = queryService;
	}

	public Object executeQuery(ExecuteQueryRequest executeQueryRequest, HttpServletResponse httpServletResponse) throws Exception {
		
		if(executeQueryRequest.getRole() == null) throw new ValidationException("Please Specify Role");
		
		String curQuery = queryService.getQuery(executeQueryRequest.getQueryId()),
					  newQuery = Constants.DEFAULT_NESTED_QUERY;
		
		QueryValidator.validateQuery(executeQueryRequest.getColumns(), curQuery.toString());
		
		newQuery = queryService.generateNativeQuery(newQuery,curQuery,executeQueryRequest.getColumns(),executeQueryRequest.getRole());
													
			
        List<HashMap<String, Object>> queryResults = entityManager.
							        		createNativeQuery(newQuery.toString()).
							        		unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
							                .getResultList();
        
        if(executeQueryRequest.getRessponseType() != null) {
        	
        	switch (executeQueryRequest.getRessponseType()) {
				case "csv":{
					ResponseOutput.outputCSV(queryResults);
					String res = ResponseOutput.readCSV(Constants.DEFAULT_LOCATION);
					httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"myCsv.csv\"");
					return res;
				}
				case "json":{
					String res = ResponseOutput.outputJSON(queryResults);
					httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"myJson.json\"");
					return res;
				}
				case "excel":{
					String res =  ResponseOutput.outputJSON(queryResults);
					httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"myXlsx.xlsx\"");
					return res;
				}
        	}
        	
        }
        return queryResults;
    }
	
}

