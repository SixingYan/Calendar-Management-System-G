package com.graviton.lambda.calendar.model;

public class Calendar {
	String name;
	int duration;
	int startDate;
	int endDate;
	int earlyTime;
	int lateTime;
	
	public Calendar () {}
	
	public Calendar (String name, int duration, int startDate, int endDate, int earlyTime, int lateTime) {
		this.name = name;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.earlyTime = earlyTime;
		this.lateTime = lateTime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	public int getEarlyTime() {
		return earlyTime;
	}
	public void setEarlyTime(int earlyTime) {
		this.earlyTime = earlyTime;
	}
	public int getLateTime() {
		return lateTime;
	}
	public void setLateTime(int lateTime) {
		this.lateTime = lateTime;
	}
	
}
