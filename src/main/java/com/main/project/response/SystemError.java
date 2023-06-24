package com.main.project.response;

public enum SystemError {

	OK(200,"The request was successfully completed."),
    
    CREATED(201,"A new resource was successfully created."),
    
    BAD_REQUEST(400,"The request was invalid."),
    
    UNIQUE(405,"Email should be unique. Alrealy existing in the database"),
    
    CUSTOM(406,""),
    
    NULL(408,"Resourse does not exist"),
    
    DATAINTEGRITY(409,"Unique data required");


    private Integer status;
    
    private String statusMessage;

    SystemError(Integer status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public Integer getStatusCode(){
        return status;
    }
    public String getStatusMessage(){
        return statusMessage;
    }

	public void setStatusCode(Integer statusCode) {
		this.status = statusCode;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}
