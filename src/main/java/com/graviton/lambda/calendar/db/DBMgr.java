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
	private static String URL = "mongodb+srv://jack:jackmongodb@cluster0-uagde.mongodb.net/test?retryWrites=true";
	private static String DATABASE = "cs509";
	private static String CLD_CLX = "calendars";
	private static String MT_CLX = "meetings";

	private MongoCollection<Document> collection;
	private Gson gson = new Gson(); // works for transfer map to pojo
	private MongoCursor<Document> cursor;
	private MongoDatabase mongoDatabase;
	private MongoClient mongoClient;
	
	public DBMgr () {
		initDB(URL, DATABASE);
	}

	public DBMgr (String url, String database) {
		initDB(url, database);
	}

	private void initDB (String url, String database) {
		this.mongoClient = new MongoClient(new MongoClientURI(url));
		this.mongoDatabase = mongoClient.getDatabase(DATABASE);
	}
	/**
	 * Show All Calendars
	 * @return
	 */
	public ArrayList<Calendar> doSAC () {
		ArrayList<Calendar> pojoList = new ArrayList<Calendar>();

		this.collection = this.mongoDatabase.getCollection(CLD_CLX);
		this.cursor = collection.find().iterator();

		while (this.cursor.hasNext()) {
			Calendar pojo = this.gson.fromJson(cursor.next().toJson(), Calendar.class);
			pojoList.add(pojo);
		}
		this.cursor.close();

		this.mongoClient.close();
		return pojoList;
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

		this.mongoClient.close();
		return new ArrayList<Calendar>();
	}

	/**
	 * Load Personal Calendar
	 * @return
	 */
	public ArrayList<Calendar> doLPC (String name) {
		ArrayList<Calendar> pojoList = new ArrayList<Calendar>();

		this.collection = this.mongoDatabase.getCollection(CLD_CLX);
		Document doc = collection.find(Filters.eq("name", name)).first();

		Calendar pojo = this.gson.fromJson(doc.toJson(), Calendar.class);
		pojoList.add(pojo);
		
		this.mongoClient.close();
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
		this.cursor.close();

		this.mongoClient.close();
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

		this.mongoClient.close();
		return new ArrayList<Meeting>();
	}

	/**
	 * Show Monthly Schedule
	 * @return
	 */
	public ArrayList<Meeting> doSMS () {
		ArrayList<Meeting> pojoList = new ArrayList<Meeting>();

		this.collection = this.mongoDatabase.getCollection(MT_CLX);
		this.cursor = collection.find(
		                  Filters.
		                  eq("name", name)).
		              eq("year", year).
		              eq("month", month).
		              iterator();

		while (this.cursor.hasNext()) {
			Meeting pojo = this.gson.fromJson(cursor.next().toJson(), Meeting.class);
			pojoList.add(pojo);
		}
		this.cursor.close();

		this.mongoClient.close();
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

		this.mongoClient.close();
		return new ArrayList<Calendar>();
	}

}
