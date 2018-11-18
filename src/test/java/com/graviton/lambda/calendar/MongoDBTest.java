package com.graviton.lambda.calendar;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
/**
 * Test connect with MongoDB with JDBC.
 * This URL is a free test cluster provided by MongoDB using MongoDB Atlas.
 * @author Jack Sixing Yan
 */
public class MongoDBTest {
	private static String URL = "mongodb+srv://jack:jackmongodb@cluster0-uagde.mongodb.net/test?retryWrites=true";
	private static String DATABASE = "cs509";
	private static String CLD_CLX = "calendars";
	
	public static void main(String[] args) {
		// 1. connect
		MongoClient mongoClient = new MongoClient(new MongoClientURI(URL));
		MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE);
		
		// 2. insert
		MongoCollection<Document> collection = mongoDatabase.getCollection(CLD_CLX);
		
		Document document = new Document("name", "personal").append("duration", 30);
		collection.insertOne(document);
		
		// 3. select
		Document myDoc = collection.find().first();
		System.out.println(myDoc.toJson());
	}
}
