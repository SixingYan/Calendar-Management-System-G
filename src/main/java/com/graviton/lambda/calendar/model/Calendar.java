package com.graviton.lambda.calendar.model;

import org.json.*;

public class Calendar {
	private String name;
	private int duration;
	private int startDate;
	private int endDate;
	private int earlyTime;
	private int lateTime;
	
	public Calendar () {}
	
	//public Calendar (JSONObject obj) {
	//	setName((String)obj.get("name"));
	//	setDuration((String)obj.get("duration"));
	//	setEarlyTime((String)obj.get("earlyTime"));
	//	setLateTime((String)obj.get("lateTime"));
	//	setStartDate((String)obj.get("startDate"));
	//	setEndDate((String)obj.get("endDate"));
	//}
	
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
	public void setDuration(String duration) {
		this.duration = Integer.valueOf(duration);
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = Integer.valueOf(startDate);
	}
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = Integer.valueOf(endDate);
	}
	public int getEarlyTime() {
		return earlyTime;
	}
	public void setEarlyTime(String earlyTime) {
		this.earlyTime = Integer.valueOf(earlyTime);
	}
	public int getLateTime() {
		return lateTime;
	}
	public void setLateTime(String lateTime) {
		this.lateTime = Integer.valueOf(lateTime);
	}
	
}
