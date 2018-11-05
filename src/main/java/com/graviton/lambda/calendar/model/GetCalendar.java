package com.graviton.lambda.calendar.model;

public class GetCalendar {
	public String name;
	public GetCalendar () {}
	
	public GetCalendar (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
