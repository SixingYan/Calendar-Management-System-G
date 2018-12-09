package com.graviton.lambda.calendar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.*;

/**
 * 
 * @author Yichen Li | Sixing Yan
 */
public class ScheduleMeeting implements RequestHandler<Meeting, ResultResponse> {
    public ResultResponse handleRequest(Meeting meeting, Context context) {
    	DBMgr db=new DBMgr();
    	
    	if (db.doSM(meeting))  
    		return new ResultResponse(true, 0, meeting.location);
    	else 
    		return new ResultResponse(false, -1, "error");
    	
    }
}
