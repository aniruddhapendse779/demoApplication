package com.example.demo.exception;

public class AppException extends RuntimeException{
	private static final long serialVersionUID = 5214686278255875385L;
	private final ErrorCode ErrorCode;
	private final String message;
	
	public AppException(ErrorCode quisqueyaErrorCode) {
		super();
		this.ErrorCode = quisqueyaErrorCode;
		this.message = quisqueyaErrorCode.getErrorMessage();
	}

	public AppException(ErrorCode quisqueyaErrorCode, String message) {
		super();
		this.ErrorCode = quisqueyaErrorCode;
		this.message = message;
	}

	public ErrorCode getQuisqueyaErrorCode() {
		return ErrorCode;
	}

	public String getQuisqueyaErrorMessage() {
		return message;
	}
}
