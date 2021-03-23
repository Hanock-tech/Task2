package com.csv2jsontask.exception;

public enum IRErrorCode {
	CATEGORY_EMPTY("402"),
	NAME_EMPTY("402"),
	AGE_EMPTY("402"),
	AGE_INVALID("402"),
	INVAIlD_REQUEST("402"),
	ERROE_MESSAGE("BadRequest");

	private String errorCode;

	IRErrorCode(String code) {
		this.errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
