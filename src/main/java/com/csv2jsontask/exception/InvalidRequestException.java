package com.csv2jsontask.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends RuntimeException {
	private String code;

	private String message;

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public InvalidRequestException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
