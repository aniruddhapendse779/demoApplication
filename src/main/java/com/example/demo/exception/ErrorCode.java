package com.example.demo.exception;

public enum ErrorCode {
	INVALID_CONFIGURATION_FORMAT(11002, "Invalid configuration JSON string."),
	NO_ACTIVE_CONFIGURATION_FOUND(11001, "No active configuration found."),
	VERSION_ALREADY_EXIST(9003, "Version already exist.");

	private int code;
	private String message;

	private ErrorCode(int errorCode, String errorMessage) {
		this.code = errorCode;
		this.message = errorMessage;
	}

	public int getErrorCode() {
		return code;
	}

	public String getErrorMessage() {
		return message;
	}
}
