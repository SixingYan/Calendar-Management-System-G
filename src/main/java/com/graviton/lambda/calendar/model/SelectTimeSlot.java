package com.graviton.lambda.calendar.model;

public class SelectTimeSlot {
	public String name;
	public int date;
	public int dow;
	public int time;
	public int duration;
	
	public SelectTimeSlot() {}
	
	public SelectTimeSlot (String name,int date,int dow,int time,int duration) {
		this.name = name;
		this.date = date;
		this.dow = dow;
		this.time = time;
		this.duration = duration;
		
	}

	
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
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
