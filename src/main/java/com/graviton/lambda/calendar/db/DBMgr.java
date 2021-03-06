package com.graviton.lambda.calendar.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.graviton.lambda.calendar.model.*;

/**
 * Use as,
 * <code>
 * import com.graviton.lambda.calendar.db.DBMgr;
 * DBMgr db = new DBMgr();
 * ArrayList<Calendar> calendars = db.doSAC(); // show all calendars
 * </code>
 * DBMgr only has one change to call any of functions,
 * after calling, it will turn down the connection.
 * This URL is a <B>free</B> test cluster provided by MongoDB using MongoDB Atlas.
 * @author Jack Sixing Yan
 */
public class DBMgr {
	private DAO dao;

	public DBMgr () {
		this.dao = new DAO();
	}

	public DBMgr (String url, String database) {
		this.dao = new DAO(url, database);
	}

	/**
	 * Show All Calendars
	 * @return
	 */
	public ArrayList<Calendar> doSAC () {
		ArrayList<Calendar> m = dao.findCalendars();
		this.dao.close();
		return m;
	}

	/**
	 * Create Personal Calendar
	 * @return
	 */
	public boolean doCPC (Calendar cld) {
		dao.addCalendar(cld.name, cld.startDate, cld.endDate, cld.earlyTime, cld.lateTime, cld.duration);
		this.dao.close();
		return true;
	}

	/**
	 * Load Personal Calendar
	 * @return
	 */
	public ArrayList<Calendar> doLPC (String name) {
		ArrayList<Calendar> m = dao.findCalendar(name);
		this.dao.close();
		return m;
	}

	/**
	 * Show Daily Schedule
	 * @return
	 */
	public ArrayList<Meeting> doSDS (PresentCalendarDaily obj) {
		ArrayList<Meeting> ms = dao.findMeetings(obj.name, obj.date);
		this.dao.close();
		return ms;
	}

	/**
	 * Show Monthly Schedule
	 * @return
	 */
	public ArrayList<Meeting> doSMS (PresentCalendarMonthly obj) {
		ArrayList<Meeting> ms = dao.findMeetings(obj.name, obj.year, obj.month);
		this.dao.close();
		return ms;
	}

	/**
	 * Schedule Meeting
	 * @return
	 */
	public boolean doSM (Meeting mt) {
		Calendar c;
		// check calendar
		if (dao.findCalendar(mt.name).isEmpty())
			return false;
		else
			c = dao.findCalendar(mt.name).get(0);
		// check basic time range
		if (c.removeDay.contains(mt.date))
			return false;
		if (!inDate(c.startDate, c.endDate, mt.date) & !c.addDay.contains(mt.date))
			return false;
		if (!inTime(c.earlyTime, c.lateTime, mt.time))
			return false;
		// check closed timeslot
		ArrayList<TimeSlot> closedTS = dao.findClosedTimeSlot(mt.name);
		for (TimeSlot cts : closedTS)
			if (!isAllowed (cts, mt))
				return false;
		// add it 
		this.dao.addMeeting(mt.name, mt.date, mt.time, mt.people, mt.location);
		this.dao.close();
		return true;
	}
	
	private boolean isAllowed (TimeSlot cts, Meeting mt) {
		if (!isNull(cts.date)) {
			if (!isNull(cts.fromTime) & !isNull(cts.toTime))
				if (!isNull(cts.dow))
					return !(inDate(cts.date, mt.date) & inTime(cts.fromTime,cts.toTime, mt.time) & inDow(cts.dow, mt.date));
				else
					return !(inDate(cts.date, mt.date) & inTime(cts.fromTime,cts.toTime, mt.time));
			else
				return !inDate(cts.date, mt.date);
			}
		else {
			if (!isNull(cts.fromTime) & !isNull(cts.toTime))
				if (!isNull(cts.dow))
					return !(inTime(cts.fromTime,cts.toTime, mt.time) & inDow(cts.dow, mt.date));
				else
					return !inTime(cts.fromTime,cts.toTime, mt.time);
			else
				return !inDow(cts.dow, mt.date);
		}
	}
	private boolean inDate (int cdate, int date) {
		if (cdate == date)
				return true;
		return false;
	}
	
	private boolean inDate (int fromDate, int toDate, int date) {
		if (fromDate <= date & toDate >= date)
			return true;
		return false;
	}
	
	private boolean isNull (int val) {
		return val == -1;
	}
	
	private boolean inTime(int fromTime, int toTime, int time) {
		if (fromTime <= time & toTime >= time)
			return true;
		return false;
	}
	
	private boolean inDow(int cdow, int date) {
		return cdow == getDow (date);
	}
	
	private int getDow (int date) {
		java.util.Calendar cld = java.util.Calendar.getInstance();
        try {
            Date datet = new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(date));
            cld.setTime(datet);
        } catch (ParseException e) {
            ;
        }
        int dow = cld.get(java.util.Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (dow < 0)
            dow = 7;
        return dow;
	}
	
	/**
	 * Close Existing Meeting
	 * @return
	 */
	public boolean doCEM (SelectMeeting obj) {
		this.dao.deleteMeeting(obj.name, obj.date, obj.time);
		this.dao.close();
		return true;
	}

	/**
	 * Delete Personal Calendar
	 * @return
	 */
	public boolean doDPC (String name) {
		this.dao.deleteCalendar(name);
		this.dao.close();
		return true;
	}

	/**
	 * Add Day
	 * @return
	 */
	public boolean doADC (String name, int date) {
		Calendar c = dao.findCalendar(name).get(0);
		if (c.removeDay.contains(date))
			c.removeDay.remove(date);
		if (!c.addDay.contains(date))
			c.addDay.add(date);
		this.dao.updateCalendar(name, c.addDay, null);
		this.dao.updateCalendar(name, null, c.removeDay);
		this.dao.close();
		return true;
	}

	/**
	 * Remove Day
	 * @return
	 */
	public boolean doRDC (String name, int date) {
		Calendar c = dao.findCalendar(name).get(0);
		if (!c.removeDay.contains(date))
			c.removeDay.add(date);
		if (c.addDay.contains(date))
			c.addDay.remove(date);

		this.dao.updateCalendar(name, c.addDay, null);
		this.dao.updateCalendar(name, null, c.removeDay);
		this.dao.close();
		return true;
	}

	/**
	 * Close TimeSlot.
	 * @return
	 */
	public boolean doCT (String name, int date, int dow, int fromTime, int toTime) {
		// check whether it exist
		if (this.dao.findCalendar(name).isEmpty())
			return false;
		// 
		this.dao.addClosedTimeSlot(name, date, dow, fromTime, toTime);
		this.dao.close();
		return true;
	}

	/**
	 * Close Exist Meeting
	 * @return
	 */
	public boolean doCEM (String name, int date, int time) {
		this.dao.deleteMeeting(name, date, time);
		this.dao.close();
		return true;
	}
}
