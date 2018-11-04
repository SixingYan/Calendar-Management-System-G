package com.graviton.lambda.calendar.model;

public class ResultResponse implements Response{
	public Status status;
	public Boolean result;
	public ResultResponse (Boolean result, Status status) {
		this.status = status;
		this.result = result;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
}
