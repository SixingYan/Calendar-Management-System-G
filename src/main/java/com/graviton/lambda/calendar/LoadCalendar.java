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
public class LoadCalendar implements RequestHandler<SelectCalendar, DataResponse> {
    public DataResponse handleRequest(SelectCalendar cld, Context context) {
    	
    	DBMgr db=new DBMgr();
    	
    	ArrayList<Calendar> list=db.doLPC(cld.getName());
    	
        return new DataResponse(list, 0, "OK");
    }
}
