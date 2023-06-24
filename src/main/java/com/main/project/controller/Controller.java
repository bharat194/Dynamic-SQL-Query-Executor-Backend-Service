package com.main.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.project.query.QueryHandler;
import com.main.project.request.AddQueryRequest;
import com.main.project.request.ExecuteQueryRequest;
import com.main.project.response.BaseResponse;
import com.main.project.response.HttpStatus;
import com.main.project.response.SystemError;
import com.main.project.service.QueryService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/query")
public class Controller {
	
	private QueryHandler queryHandler;
	
	private QueryService queryService;
	
	public Controller(QueryHandler queryHandler, QueryService queryService){
		super();
		this.queryHandler = queryHandler;
		this.queryService = queryService;
	}
	
	@PostMapping("/executeQuery")
	public Object getRes(@RequestBody ExecuteQueryRequest executeQueryRequest,  HttpServletResponse httpServletResponse) throws Exception {
		
		return executeQueryRequest.getRessponseType() != null? queryHandler.executeQuery(executeQueryRequest,httpServletResponse) : BaseResponse.builder().
																						 queryResult(queryHandler.executeQuery(executeQueryRequest,httpServletResponse)).
																						 httpStatus(new HttpStatus(SystemError.OK)).
																						 build();
	}
	
	@PostMapping("/addQuery")
	public BaseResponse addQueryDump(@RequestBody AddQueryRequest addQueryRequest) {
		
		queryService.addQuery(addQueryRequest);
		
		return BaseResponse.builder().
				httpStatus(new HttpStatus(SystemError.OK)).
				build();
	}
	
	@GetMapping("/randomQuery")
	public Object getRes(HttpServletResponse httpServletResponse) throws Exception {
		return queryHandler.executeQuery(ExecuteQueryRequest.builder().columns(null).queryId(603l).ressponseType(null).role("Admin").build(), httpServletResponse);
	}
}
