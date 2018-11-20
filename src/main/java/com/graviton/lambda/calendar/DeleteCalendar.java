package com.graviton.lambda.calendar;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.*;
/**
 * 
 * @author Yichen Li | Sixing Yan
 */
public class DeleteCalendar implements RequestHandler<SelectCalendar, ResultResponse> {
    public ResultResponse handleRequest(SelectCalendar cld, Context context) {
    	DBMgr db=new DBMgr();
    	
    	String name=cld.getName();
    	
    	ArrayList<Calendar> result = db.doDPC(name);
    	
    	if (result.getClass()==ArrayList.class) {
    		return new ResultResponse(true, 0, "OK");
    	}else {
    		return new ResultResponse(true, 0, "error");
    	}
    }
}
