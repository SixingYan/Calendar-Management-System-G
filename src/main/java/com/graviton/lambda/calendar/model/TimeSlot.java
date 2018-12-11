package com.graviton.lambda.calendar.model;

public class TimeSlot {
	public String name;
	public int date;
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
	
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
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
