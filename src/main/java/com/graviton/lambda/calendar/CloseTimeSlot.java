package com.graviton.lambda.calendar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.ResultResponse;
import com.graviton.lambda.calendar.model.SelectTimeSlot;

public class CloseTimeSlot implements RequestHandler<SelectTimeSlot, ResultResponse> {

    @Override
    public ResultResponse handleRequest(SelectTimeSlot sts, Context context) {      
    	DBMgr db=new DBMgr();
    	
    	String name=sts.getName();    	
    	int date=sts.getDate();
    	int dow=sts.getDow();
    	int time=sts.getTime();
    	int duration=sts.getDuration();

    	
    	if(db.doCT(name,date,dow,time,duration))
    		return new ResultResponse(true, 0, "OK");
    	else
    		return new ResultResponse(false, -1, "error");
    	
    }

}
