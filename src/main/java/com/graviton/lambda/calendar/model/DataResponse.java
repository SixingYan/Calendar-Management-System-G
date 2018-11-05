package com.graviton.lambda.calendar.model;

import java.util.HashMap;

public class DataResponse {
	public int code;
	public String message;
	public HashMap data;
	public DataResponse () {}
	public DataResponse (HashMap data, int code, String message) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	
}
