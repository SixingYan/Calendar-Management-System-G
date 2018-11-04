package com.graviton.lambda.calendar;

import java.util.HashMap;
import com.graviton.lambda.calendar.model.Calendar;
import com.graviton.lambda.calendar.model.Status;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
/**
 * 需要相应的修改CreateCalendarTest里的代码，使其的input/output的类型对应上——这里是HashMap.
 */
public class CreateCalendar implements RequestHandler<HashMap, HashMap> {
    @Override
    public HashMap handleRequest(HashMap Calendar, Context context) {
        //1. log 
    	// context.getLogger().log("Input: " + input);
    	
    	//2. deal with input request
    	// Void now.
    	
    	//3. generate output response
        HashMap<String, Object> respose = new HashMap<String, Object>();
        Status status = new Status(0, "OK");
        
        respose.put("status", status);
        respose.put("result", true);
        return respose;
    }

}
