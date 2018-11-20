package com.graviton.lambda.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import com.graviton.lambda.calendar.db.DBMgr;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.model.*;

public class CancelMeeting implements RequestHandler<SelectMeeting, ResultResponse> {

    public ResultResponse handleRequest(SelectMeeting sm, Context context) {
    	
    	DBMgr db = new DBMgr();
    	
    	ArrayList result = db.doCEM(sm);
    	
    	if (result.getClass() == ArrayList.class) {
    		return new ResultResponse(true, 0, "OK");
    	} else {
    		return new ResultResponse(false, -1, "error");
    	}
    }
}