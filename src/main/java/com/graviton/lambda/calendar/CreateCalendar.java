package com.graviton.lambda.calendar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.graviton.lambda.calendar.model.*;

/**
 * 
 */
public class CreateCalendar implements RequestHandler<Calendar, ResultResponse> {
    //@Override
    public ResultResponse handleRequest(Calendar cld, Context context) {
        //1. log 
    	
    	//2. deal with input request
    	
        //3. generate output response
        return new ResultResponse(true, 0, "OK");
    }
}
