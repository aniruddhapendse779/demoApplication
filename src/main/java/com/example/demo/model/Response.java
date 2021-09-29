package com.example.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Response implements Serializable {

	private static final long serialVersionUID = 3442955473544649871L;
	private static final int STATUS_SUCCESS = 200;
	public static final String SUCCESS = "Success";

	private int status; // 0 = SUCCESS ELSE ERROR_CODE
	private String msg;
	private Object data;// NOSONAR

	/**
	 * default constructor
	 */
	public Response() {

	}

	public Response(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static Response getSuccessResponse() {
		return new Response(STATUS_SUCCESS, SUCCESS);
	}

	public static Response getSuccessResponse(String msg) {
		Response response = getSuccessResponse();
		response.setMsg(msg);
		return response;
	}

	public static Response getSuccessResponse(Object data) {
		Response response = getSuccessResponse();
		response.setData(data);
		return response;
	}

	public static Response getSuccessResponse(Object data, String msg) {
		Response response = getSuccessResponse();
		response.setData(data);
		response.setMsg(msg);
		return response;
	}

//	public static Response getFailureResponse(ErrorCode error) {
//		return new Response(error.getErrorCode(), error.getErrorMessage());
//	}
//
//	public static Response getFailureResponse(ErrorCode error, String message) {
//		return new Response(error.getErrorCode(), message);
//	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			// ignore
		}
		return super.toString();
	}

}