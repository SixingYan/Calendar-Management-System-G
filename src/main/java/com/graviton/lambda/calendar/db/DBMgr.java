package com.graviton.lambda.calendar.db;

import java.util.ArrayList;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import com.google.gson.*;
import com.graviton.lambda.calendar.model.*;

/**
 * use as: com.graviton.lambda.calendar.db.DBMgr
 * DBMgr db = new DBMgr();
 * ArrayList<Calendar> calendars = db.doSAC(); // show all calendars
 * @author Jack Sixing Yan
 *
 */
public class DBMgr {
	private static String URL = "localhost:27017";
	private static String DATABASE = "cs509";

	private static String CLD_CLX = "calendars";
	private static String MT_CLX = "meetings";

	private MongoCollection<Document> collection;
	private Gson gson = new Gson(); // works for map to pojo
	private MongoCursor<Document> cursor;
	private MongoDatabase mongoDatabase;

	public DBMgr () {
		initDB(URL, DATABASE);
	}

	public DBMgr (String url, String database) {
		initDB(url, database);
	}

	private void initDB (String url, String database) {
		MongoClient mongoClient = MongoClients.create(new ConnectionString(url));

		this.mongoDatabase = mongoClient.getDatabase(DATABASE);
		//this.collection = mongoDatabase.getCollection(COLLECTION);
	}
	/**
	 * Show All Calendars
	 * @return
	 */
	public ArrayList<Calendar> doSAC () {
		return 
	}
	
	/**
	 * Create Personal Calendar
	 * @return
	 */
	public ArrayList<Calendar> doCPC (Calendar cld) {
		this.collection = this.mongoDatabase.getCollection(CLD_CLX);

		Document document = new Document("name", cld.name).
		append("startDate", cld.startDate).
		append("endDate", cld.endDate).
		append("startTime", cld.earlyTime).
		append("endTime", cld.lateTime).
		append("duration", cld.duration);

		this.collection.insertOne(document);

		return new ArrayList<Calendar>();
	}

	/**
	 * Load Personal Calendar
	 * @return
	 */
	public ArrayList<Calendar> doLPC (String name) {
		ArrayList<Calendar> pojoList = new ArrayList<Calendar>();

		this.collection = this.mongoDatabase.getCollection(CLD_CLX);
		this.cursor = collection.find(Filters.eq("name", name)).iterator();

		while (this.cursor.hasNext()) {
			Calendar pojo = this.gson.fromJson(cursor.next().toJson(), Calendar.class);
			pojoList.add(pojo);
		}

		return pojoList;
	}

	/**
	 * Show Daily Schedule
	 * @return
	 */
	public ArrayList<Meeting> doSDS () {
		ArrayList<Meeting> pojoList = new ArrayList<Meeting>();

		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.cursor = collection.find(Filters.
		                              eq("name", name)).
		              eq("date", date).
		              iterator();

		while (this.cursor.hasNext()) {
			Meeting pojo = this.gson.fromJson(cursor.next().toJson(), Meeting.class);
			pojoList.add(pojo);
		}

		return pojoList;
	}

	/**
	 * Schedule Meeting
	 * @return
	 */
	public ArrayList<Meeting> doSM (Meeting mt) {

		Document document = new Document("name", mt.name).
		append("date", mt.date).
		append("time", mt.time).
		append("people", mt.people).
		append("location", mt.location);

		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.collection.insertOne(document);

		return new ArrayList<Meeting>();
	}

	/**
	 * Show Monthly Schedule
	 * @return
	 */
	public ArrayList<Meeting> doSMS () {
		ArrayList<Meeting> pojoList = new ArrayList<Meeting>();

		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.cursor = collection.find(Filters.
		                              eq("name", name)).
		              eq("year", year).
		              eq("month", month).
		              iterator();

		while (this.cursor.hasNext()) {
			Meeting pojo = this.gson.fromJson(cursor.next().toJson(), Meeting.class);
			pojoList.add(pojo);
		}

		return pojoList;
	}

	/**
	 * Close Existing Meeting
	 * @return
	 */
	public ArrayList<Meeting> doCEM () {
		this.collection.deleteOne(Filters.eq("name", name).eq("date", date).eq("time", time));
		return new ArrayList<Meeting>();
	}

	/**
	 * Delete Personal Calendar
	 * @return
	 */
	public ArrayList<Calendar> doDPC (String name) {
		this.collection.deleteOne(Filters.eq("name", name));
		return new ArrayList<Calendar>();
	}

}
