package com.graviton.lambda.calendar.model;


public class SelectMeeting {
	public String name;
	public int date;
	public int time;
	public SelectMeeting () {}
	
	public SelectMeeting (String name, int date, int time) {
		this.name = name;
		this.date = date;
		this.time = time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
}
