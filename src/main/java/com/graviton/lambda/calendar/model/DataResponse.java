package com.graviton.lambda.calendar.model;

import java.util.HashMap;

public class DataResponse implements Response {
	public Status status;
	public HashMap data;
	public DataResponse (Status status, HashMap data) {
		this.status = status;
		this.data = data;
	} 
}
