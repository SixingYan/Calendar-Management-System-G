package com.graviton.lambda.calendar.db;

import java.util.ArrayList;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import com.google.gson.*;
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

	private MongoCollection<Document> collection;

	private Gson gson = new Gson(); // works for transfer map to pojo
	private MongoCursor<Document> cursor;
	private MongoDatabase mongoDatabase;
	private MongoClient mongoClient;

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
		return dao.findCalendars();
	}

	/**
	 * Create Personal Calendar
	 * @return
	 */
	public boolean doCPC (Calendar cld) {
		return dao.addCalendar(cld.name, cld.startDate, cld.endDate, cld.earlyTime, cld.lateTime, cld.duration);
	}

	/**
	 * Load Personal Calendar
	 * @return
	 */
	public ArrayList<Calendar> doLPC (String name) {
		return dao.findCalendar(name);
	}

	/**
	 * Show Daily Schedule
	 * @return
	 */
	public ArrayList<Meeting> doSDS (PresentCalendarDaily obj) {
		return dao.findMeetings(obj.name, obj.date);
	}

	/**
	 * Show Monthly Schedule
	 * @return
	 */
	public ArrayList<Meeting> doSMS (PresentCalendarMonthly obj) {
		return dao.findMeetings(obj.name, obj.year, obj.month);
	}

	/**
	 * Schedule Meeting
	 * @return
	 */
	public boolean doSM (Meeting mt) {
		
		if (dao.findCalendar(mt.name).isEmpty())
			return false;
		else
			Calendar c = dao.findCalendar(mt.name).get(0);

		if (c.removeDay.contains(mt.date))
			return false;
		
		boolean isAllowed = true;
		ArrayList<TimeSlot> closedTS = dao.findClosedTimeSlot(mt.name);

		java.util.Calendar cld = java.util.Calendar.getInstance();
        try {
            Date datet = new SimpleDateFormat("yyyyMMdd").parse(String.valueOf(mt.date));
            cld.setTime(datet);
        } catch (ParseException e) {
            return false;
        }
        int dow = cld.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (dow < 0)
            dow = 7;

		for (TimeSlot ts : closedTS) {
			//check date
			if (ts.startDate )

			// chcek dow

			// check time

		}

		this.dao.addMeeting(mt.name, mt.date, mt.time, mt.people, mt.location);
		return true;
	}

	/**
	 * Close Existing Meeting
	 * @return
	 */
	public ArrayList<Meeting> doCEM (SelectMeeting obj) {
		this.dao.deleteMeeting(obj.name, obj.date, obj.time);
		return true;
	}

	/**
	 * Delete Personal Calendar
	 * @return
	 */
	public boolean doDPC (String name) {
		this.dao.deleteCalendar(name);
		return true;
	}

	/**
	 * Add Day
	 * @return
	 */
	public boolean doADC (String name, int date) {
		Calendar c = dao.findCalendar().get(0);
		if (c.removeDay.contains(date))
			c.removeDay.remove(date);
		if (!c.addDay.contains(date))
			c.addDay.add(date);

		this.dao.updateCalendar(name, c.addDay, null);
		this.dao.updateCalendar(name, null, c.removeDay);

		return true;
	}

	/**
	 * Remove Day
	 * @return
	 */
	public boolean doRDC (String name, int date) {
		Calendar c = dao.findCalendar().get(0);
		if (!c.removeDay.contains(date))
			c.removeDay.add(date);
		if (c.addDay.contains(date))
			c.addDay.remove(date);

		this.dao.updateCalendar(name, c.addDay, null);
		this.dao.updateCalendar(name, null, c.removeDay);

		return true;
	}

	/**
	 * Close TimeSlot.
	 * @return
	 */
	public boolean doCT (String name, int fromMonth, int toMonth, int fromDay, int toDay, int dow, int fromTime, int toTime) {

		// check whether it exist
		if (this.dao.findCalendar(name).isEmpty() == null)
			return false;
		//
		this.dao.addClosedTimeSlot(name, fromMonth, toMonth, fromDay, toDay, dow, fromTime, toTime);

		return true;
	}

	/**
	 * Close Exist Meeting
	 * @return
	 */
	public boolean doCEM (String name, int date, int time) {
		this.dao.deleteMeeting(name, date, time);
		return true;
	}
}
