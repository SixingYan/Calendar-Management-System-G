package com.graviton.lambda.calendar.model;

import org.json.*;

public class Calendar {
	public String name;
	public int duration;
	public int startDate;
	public int endDate;
	public int earlyTime;
	public int lateTime;
	
	public Calendar () {}
	
	public Calendar (String name, int duration, int earlyTime, int lateTime, int startDate, int endDate) {
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
