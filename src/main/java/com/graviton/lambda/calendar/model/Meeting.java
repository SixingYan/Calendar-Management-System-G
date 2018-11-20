package com.graviton.lambda.calendar.model;
/**
 * 
 * @author  | Sixing Yan
 *
 */
public class Meeting {
	
	public String name;
	public int date;
	public int time;
	public String people;
	public String location = "Fuller";
	
	public Meeting() {}
	
	public Meeting(String name, int date, int time, String people, String location) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.people = people;
		this.location = location;
	}
	
	public void setDate(int date) {
		this.date = date;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setPeople(String people) {
		this.people = people;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
}

