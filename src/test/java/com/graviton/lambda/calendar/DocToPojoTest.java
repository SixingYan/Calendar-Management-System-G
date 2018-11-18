package com.graviton.lambda.calendar;

import org.bson.Document;

import com.google.gson.Gson;
import com.graviton.lambda.calendar.model.Calendar;
/**
 * Test transferring the Document Class to a POJO Class
 * @author Jack Sixing Yan
 */
public class DocToPojoTest {
	
	public static void main(String[] args) {
		Gson gson = new Gson();
		Document document = new Document("name", "personal").append("duration", 30);
		Calendar pojo = gson.fromJson(document.toJson(), Calendar.class);
		System.out.println(pojo.getName());
	}
}
