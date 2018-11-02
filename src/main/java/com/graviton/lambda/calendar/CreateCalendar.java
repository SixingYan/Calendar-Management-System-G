package com.graviton.lambda.calendar;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateCalendar implements RequestHandler<HashMap, HashMap> {
    @Override
    public HashMap handleRequest(HashMap request, Context context) {
        //context.getLogger().log("Input: " + input);
        HashMap<String, Object> respose = new HashMap<String, Object>();
        
        HashMap<String, Object> status = new HashMap<String, Object>();
        status.put("code", 0);
        status.put("msg", "OK");
        
        respose.put("status", status);
        respose.put("result", true);
        return respose;
    }

}
