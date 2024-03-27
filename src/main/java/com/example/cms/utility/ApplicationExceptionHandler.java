package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.exceptions.UserAlreadyExistByEmailException;

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
	
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmailException(
			UserAlreadyExistByEmailException ex){
		return errorResponce(HttpStatus.BAD_REQUEST, ex.getMessage(), "Userdata is already present...");
	}

}
