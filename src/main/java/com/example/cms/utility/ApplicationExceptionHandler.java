package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.exceptions.TitleNotAvailableException;
import com.example.cms.exceptions.TopicNotSpecifiedException;
import com.example.cms.exceptions.UserAlreadyExistByEmailException;
import com.example.cms.exceptions.UserNotFoundException;
import com.example.cms.exceptions.handleInternalServerError;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure<String> structure;
	
	public ResponseEntity<ErrorStructure<String>> errorResponce(HttpStatus status, String message, String rootCause){
		return new ResponseEntity<ErrorStructure<String>>(structure
				.setErrorCode(status.value())
				.setMessage(message)
				.setRootCause(rootCause), status);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmailException(UserAlreadyExistByEmailException ex){
		return errorResponce(HttpStatus.BAD_REQUEST, ex.getMessage(), "Userdata is already present...");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> UserNotFoundExceptionHandler(UserNotFoundException ex){
		return errorResponce(HttpStatus.BAD_REQUEST, ex.getMessage(), "User is NOT FOUND...");
	}
	

//    public ResponseEntity<ResponseStructure<String>> handleInternalServerError(handleInternalServerError ex) {
//    	return errorResponce(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "An error occurred while creating the blog");
//      }
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> TitleNotAvailableExceptionHandler(TitleNotAvailableException ex){
		return errorResponce(HttpStatus.BAD_REQUEST, ex.getMessage(), "Title is Not Avaialable...");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> TopicNotSpecifiedExceptionHandler(TopicNotSpecifiedException ex){
		return errorResponce(HttpStatus.BAD_REQUEST, ex.getMessage(), "Topic has not been specified..");
	}
	
	
	
}
