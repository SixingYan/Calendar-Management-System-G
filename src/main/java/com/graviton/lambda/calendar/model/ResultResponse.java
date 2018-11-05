package com.graviton.lambda.calendar.model;

public class ResultResponse {
	public int code;
	public String message;
	public Boolean result;
	public ResultResponse () {}
	public ResultResponse (Boolean result, int code, String message) {
		this.result = result;
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	
}
