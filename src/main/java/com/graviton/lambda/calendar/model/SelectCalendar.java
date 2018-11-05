package com.graviton.lambda.calendar.model;

public class SelectCalendar {
	public String name;
	public SelectCalendar () {}
	
	public SelectCalendar (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
