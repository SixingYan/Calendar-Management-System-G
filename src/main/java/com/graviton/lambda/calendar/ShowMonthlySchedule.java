package com.graviton.lambda.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class ShowMonthlySchedule implements RequestHandler<PresentCalendarMonthly, DataResponse> {
	
    public DataResponse handleRequest(PresentCalendarMonthly pcm, Context context) {
    	DBMgr db = new DBMgr();
    	
    	ArrayList<Meeting> ms = db.doSMS(pcm);
    	
    	return new DataResponse(ms, 0, "OK");
    }
}

