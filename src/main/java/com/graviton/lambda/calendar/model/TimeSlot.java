package com.graviton.lambda.calendar.model;

public class TimeSlot {
	public String name;
	public int fromDate;
	public int toDate;
	public int dow;
	public int fromTime;
	public int toTime;
	
	public TimeSlot () {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFromDate() {
		return fromDate;
	}
	public void setFromDate(int fromDate) {
		this.fromDate = fromDate;
	}
	public int getToDate() {
		return toDate;
	}
	public void setToDate(int toDate) {
		this.toDate = toDate;
	}
	public int getDow() {
		return dow;
	}
	public void setDow(int dow) {
		this.dow = dow;
	}
	public int getFromTime() {
		return fromTime;
	}
	public void setFromTime(int fromTime) {
		this.fromTime = fromTime;
	}
	public int getToTime() {
		return toTime;
	}
	public void setToTime(int toTime) {
		this.toTime = toTime;
	}
	
}
