package com.graviton.lambda.calendar.model;

public class PresentCalendarMonthly {
	
	public int year;
	public int month;
	public String name;
	
	public PresentCalendarMonthly() {}
	
	public PresentCalendarMonthly(int year, int month, String name) {
		this.year = year;
		this.month = month;
		this.name = name;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setCalendarName(String name) {
		this.name = name;
	}
}
