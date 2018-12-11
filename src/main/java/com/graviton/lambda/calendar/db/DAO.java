package com.graviton.lambda.calendar.db;

import java.util.ArrayList;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import com.google.gson.*;
import com.graviton.lambda.calendar.model.*;

/**
 * 
 * @author Sixing Yan
 */
public class DAO {
	private static String URL = "mongodb+srv://jack:jackmongodb@cluster0-uagde.mongodb.net/test?retryWrites=true";
	private static String DATABASE = "cs509";
	private static String CLD_CLX = "calendars";
	private static String MT_CLX = "meetings";
	private static String CTS_CLX = "closedtimeslot"; // not primary key

	private MongoCollection<Document> collection;
	private Gson gson = new Gson(); // works for transfer map to pojo
	private MongoCursor<Document> cursor;
	private MongoDatabase mongoDatabase;
	private MongoClient mongoClient;

	public DAO () {
		initDB(URL, DATABASE);
	}

	public DAO (String url, String database) {
		initDB(url, database);
	}

	private void initDB (String url, String database) {
		this.mongoClient = new MongoClient(new MongoClientURI(url));
		this.mongoDatabase = mongoClient.getDatabase(DATABASE);
	}

// calendar
	public ArrayList<Calendar> findCalendar (String name) {
		ArrayList<Calendar> pojoList = new ArrayList<Calendar>();

		this.collection = this.mongoDatabase.getCollection(CLD_CLX);
		Document doc = collection.find(Filters.eq("name", name)).first();

		Calendar pojo = this.gson.fromJson(doc.toJson(), Calendar.class);
		pojoList.add(pojo);

		this.mongoClient.close();
		return pojoList;
	}

	public ArrayList<Calendar> findCalendars () {
		ArrayList<Calendar> pojoList = new ArrayList<Calendar>();

		this.collection = this.mongoDatabase.getCollection(CLD_CLX);
		this.cursor = this.collection.find().iterator();

		while (this.cursor.hasNext()) {
			Calendar pojo = this.gson.fromJson(cursor.next().toJson(), Calendar.class);
			pojoList.add(pojo);
		}
		this.cursor.close();
		this.mongoClient.close();
		return pojoList;
	}

	public void addCalendar (String name, int startDate, int endDate, int earlyTime, int lateTime, int duration) {
		this.collection = this.mongoDatabase.getCollection(CLD_CLX);

		Document document = new Document("name", name).
		append("startDate", startDate).
		append("endDate", endDate).
		append("earlyTime", earlyTime).
		append("lateTime", lateTime).
		append("duration", duration);

		this.collection.insertOne(document);
		this.mongoClient.close();
	}

	public void updateCalendar (String name, ArrayList<Integer> addDay, ArrayList<Integer> removeDay) {
		

		if (addDay != null)
			this.collection.updateOne(Filters.eq("name", name), Updates.set("addDay", addDay));

		if (removeDay != null)
			this.collection.updateOne(Filters.eq("name", name), Updates.set("removeDay", removeDay));
	}

	public void deleteCalendar (String name) {
		this.collection = this.mongoDatabase.getCollection(CLD_CLX);
		this.collection.deleteOne(Filters.eq("name", name));
		this.mongoClient.close();
	}


// meetings
	public ArrayList<Meeting> findMeetings (String name, int date) {

		ArrayList<Meeting> pojoList = new ArrayList<Meeting>();

		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.cursor = collection.find(Filters.and(
		                                  Filters.eq("name", name),
		                                  Filters.eq("date", date))).
		              iterator();

		while (this.cursor.hasNext()) {
			Meeting pojo = this.gson.fromJson(cursor.next().toJson(), Meeting.class);
			pojoList.add(pojo);
		}
		this.cursor.close();

		this.mongoClient.close();
		return pojoList;
	}
	public ArrayList<Meeting> findMeetings (String name, int year, int month) {
		ArrayList<Meeting> pojoList = new ArrayList<Meeting>();

		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.cursor = collection.find(
		                  Filters.and(
		                      Filters.gte("date", year * 10000 + month * 100),
		                      Filters.lt("date", year * 10000 + (month + 1) * 100)))
		              .iterator();

		while (this.cursor.hasNext()) {
			Meeting pojo = this.gson.fromJson(cursor.next().toJson(), Meeting.class);
			pojoList.add(pojo);
		}
		this.cursor.close();

		this.mongoClient.close();
		return pojoList;
	}

	public void addMeeting (String name, int date, int time, String people, String location) {
		Document document = new Document("name", name).
		append("date", date).
		append("time", time).
		append("people", people).
		append("location", location);

		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.collection.insertOne(document);

		this.mongoClient.close();

	}

	public void deleteMeeting (String name, int date, int time) {
		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.collection.deleteOne(Filters.and(
		                              Filters.eq("name", name),
		                              Filters.eq("date", date),
		                              Filters.eq("time", time)));
		this.mongoClient.close();
	}

// closed time slot
	public ArrayList<TimeSlot> findClosedTimeSlot (String name) {
		ArrayList<TimeSlot> pojoList = new ArrayList<TimeSlot>();
		this.collection = this.mongoDatabase.getCollection(CTS_CLX);
		Document doc = collection.find(Filters.eq("name", name)).first();

		TimeSlot pojo = this.gson.fromJson(doc.toJson(), TimeSlot.class);
		pojoList.add(pojo);

		this.mongoClient.close();
		return pojoList;
	}

	public void addClosedTimeSlot (String name, int date, int dow, int fromTime, int toTime) {
		Document document = new Document("name", name);

		if (date != -1) {
			document.append("date", date);
		}

		if (dow != -1) {
			document.append("dow", dow);
		}

		if (fromTime != -1 & toTime != -1) {
			document.append("fromTime", fromTime);
			document.append("toTime", toTime);
		}

		this.collection = this.mongoDatabase.getCollection(CTS_CLX);
		this.collection.insertOne(document);

		this.mongoClient.close();
	}

}
