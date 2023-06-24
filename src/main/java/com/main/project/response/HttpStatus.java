package com.main.project.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpStatus {

	private Integer status;
	
	private String message;
	
	public HttpStatus(SystemError systemError){
		super();
		this.status = systemError.getStatusCode();
		this.message = systemError.getStatusMessage();
	}
	
}
