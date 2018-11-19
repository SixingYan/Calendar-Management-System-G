package com.graviton.lambda.calendar.model;

import java.util.ArrayList;
import java.util.HashMap;

public class DataResponse {
	public int code;
	public String message;
	public ArrayList data;
	public DataResponse () {}
	public DataResponse (ArrayList data, int code, String message) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	
}
