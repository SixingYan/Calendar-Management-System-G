package com.graviton.lambda.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.model.*;

public class ShowDailySchedule implements RequestHandler<PresentCalendarDaily, DataResponse> {

    public DataResponse handleRequest(PresentCalendarDaily pcd, Context context) {
    	
    	DBMgr db = new DBMgr();
        
        ArrayList<Meeting> ms = db.doSDS(pcd);
       
    	return new DataResponse(ms, 0, "OK");
    }
}

