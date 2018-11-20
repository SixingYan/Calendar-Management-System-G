package com.graviton.lambda.calendar.model;


public class PresentCalendarDaily {
	public int date;
	public String name;

	public PresentCalendarDaily() {}

	public PresentCalendarDaily(int date, String name) {
		this.date = date;
		this.name = name;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public void setCalendarName(String name) {
		this.name = name;
	}
}

