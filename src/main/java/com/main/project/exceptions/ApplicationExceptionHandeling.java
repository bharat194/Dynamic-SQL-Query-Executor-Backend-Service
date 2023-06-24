package com.main.project.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.main.project.response.BaseResponse;
import com.main.project.response.HttpStatus;
import com.main.project.response.SystemError;

import jakarta.xml.bind.ValidationException;

@RestControllerAdvice
public class ApplicationExceptionHandeling {

	@ExceptionHandler(OptimisticLockingFailureException.class)
	public BaseResponse HandleIllegalArgumentException(OptimisticLockingFailureException ex) {
		return BaseResponse.builder().httpStatus(new HttpStatus(SystemError.BAD_REQUEST)).build();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public BaseResponse HandleIllegalArgumentException(IllegalArgumentException ex) {
		return BaseResponse.builder().httpStatus(new HttpStatus(SystemError.NULL)).build();
	}

	@ExceptionHandler(ValidationException.class)
    public BaseResponse HandlevalidationException(ValidationException e) {
		BaseResponse baseResponse = BaseResponse.builder().httpStatus(new HttpStatus(SystemError.CUSTOM)).build();
		baseResponse.getHttpStatus().setMessage(e.getMessage());		 
    	return baseResponse;
    }
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse HandleconstraintVoilationEx(HttpMessageNotReadableException e) {
		BaseResponse baseResponse = BaseResponse.builder().httpStatus(new HttpStatus(SystemError.CUSTOM)).build();
		baseResponse.getHttpStatus().setMessage(e.getMessage());		 
    	return baseResponse;
    }
    
    @ExceptionHandler(PSQLException.class)
    public BaseResponse HandleconstraintVoilationEx(PSQLException e) {
		BaseResponse baseResponse = BaseResponse.builder().httpStatus(new HttpStatus(SystemError.CUSTOM)).build();
		baseResponse.getHttpStatus().setMessage(e.getMessage());		 
    	return baseResponse;
    }
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public BaseResponse HandleDataIntegrityViolationException(DataIntegrityViolationException e) {		 
    	return BaseResponse.builder().httpStatus(new HttpStatus(SystemError.DATAINTEGRITY)).build();
    }

    @ExceptionHandler({ConstraintViolationException.class})
     public BaseResponse HandleconstraintVoilationEx(Exception e) {
    	return BaseResponse.builder().httpStatus(new HttpStatus(SystemError.UNIQUE)).build();
    }

}
