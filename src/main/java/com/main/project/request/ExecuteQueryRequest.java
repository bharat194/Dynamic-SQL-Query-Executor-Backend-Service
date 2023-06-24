package com.main.project.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteQueryRequest {
	
	private Long queryId;
	
	private List<String> columns;
	
	private String ressponseType;
	
	private String role;

}
