package com.graviton.lambda.calendar;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.*;

/**
 * 
 * @author 
 *
 */
public class ScheduleMeeting implements RequestHandler<Meeting, ResultResponse> {
	
    public ResultResponse handleRequest(Meeting meeting, Context context) {
    	
    	DBMgr db=new DBMgr();
    	
    	ArrayList<Meeting> result = db.doSM(meeting);
    	
    	if (result.getClass() == ArrayList.class)  
    		return new ResultResponse(true, 0, meeting.location);
    	else 
    		return new ResultResponse(false, -1, "error");
    	
    }
}
