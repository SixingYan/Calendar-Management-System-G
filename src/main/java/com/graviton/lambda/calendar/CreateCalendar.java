package com.graviton.lambda.calendar;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.*;

/**
 * @author Yichen Li | Sixing Yan
 */
public class CreateCalendar implements RequestHandler<Calendar, ResultResponse> {
    //@Override
    public ResultResponse handleRequest(Calendar cld, Context context) {
        
    	DBMgr db=new DBMgr();
    	ArrayList<Calendar> result=db.doCPC(cld);
    	
    	if(result.getClass()==ArrayList.class)
    		return new ResultResponse(true, 0, "OK");
    	else
    		return new ResultResponse(false, -1, "error");
    	
        
    }
}
