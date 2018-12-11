package com.graviton.lambda.calendar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.ResultResponse;
import com.graviton.lambda.calendar.model.SelectCalendar;
import com.graviton.lambda.calendar.model.SelectDay;

public class RemoveDayFromCalendar implements RequestHandler<SelectDay, ResultResponse>  {

	 public ResultResponse handleRequest(SelectDay sd, Context context) {        
	    
		 DBMgr db=new DBMgr();
	    	
	    	String name=sd.getName();
	    	int date=sd.getDate();

	    	if(db.doRDC(name,date))
	    		return new ResultResponse(true, 0, "OK");
	    	else
	    		return new ResultResponse(false, -1, "error");
	    	
	        
	    }
}