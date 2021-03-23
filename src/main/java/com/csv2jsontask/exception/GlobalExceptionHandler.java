package com.csv2jsontask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorResponse> invalidRequestException(InvalidRequestException ex, WebRequest request) {

		ErrorResponse response = new ErrorResponse();
		response.setCode(ex.getCode());
		response.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
