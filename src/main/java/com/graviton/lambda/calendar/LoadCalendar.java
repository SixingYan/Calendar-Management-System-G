package com.graviton.lambda.calendar;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.model.*;
public class LoadCalendar implements RequestHandler<SelectCalendar, DataResponse> {

    @Override
    public DataResponse handleRequest(SelectCalendar cld, Context context) {
    	HashMap calendar = new HashMap();
    	calendar.put("name", cld.name);
    	calendar.put("duration", 30);
    	calendar.put("earlyTime", 1000);
    	calendar.put("lateTime", 1700);
    	calendar.put("startDate", 20180900);
    	calendar.put("endDate", 20181214);
    	
        return new DataResponse(calendar, 0, "OK");
    }

}
