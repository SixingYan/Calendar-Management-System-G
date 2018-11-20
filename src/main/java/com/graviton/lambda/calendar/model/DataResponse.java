package com.graviton.lambda.calendar.model;

import java.util.List;
/**
 * 
 * @author Sixing Yan
 *
 */
public class DataResponse {
	public int code;
	public String message;
	public List data;
	public DataResponse () {}
	public DataResponse (List data, int code, String message) {
		this.code = code;
		this.message = message;
		this.data = data;
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
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	
}
