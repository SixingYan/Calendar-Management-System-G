package com.graviton.lambda.calendar;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
/**
 * 需要相应的修改CreateCalendarTest里的代码，使其的input/output的类型对应上——这里是HashMap.
 */
public class CreateCalendar implements RequestHandler<HashMap, HashMap> {
    @Override
    public HashMap handleRequest(HashMap request, Context context) {
        //1. log 
    	// context.getLogger().log("Input: " + input);
    	
    	//2. deal with input request
    	// Void now.
    	
    	//3. generate output response
        HashMap<String, Object> respose = new HashMap<String, Object>();
        
        HashMap<String, Object> status = new HashMap<String, Object>();
        status.put("code", 0);
        status.put("msg", "OK");
        
        respose.put("status", status);
        respose.put("result", true);
        return respose;
    }

}
