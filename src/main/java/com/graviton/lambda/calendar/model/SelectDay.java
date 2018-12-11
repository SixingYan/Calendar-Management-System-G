package com.graviton.lambda.calendar.model;

public class SelectDay {
	public String name;
	public int date;
	public SelectDay() {
		
	}
	public SelectDay(int date) {
		this.date=date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
