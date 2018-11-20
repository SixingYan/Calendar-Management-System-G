package com.graviton.lambda.calendar;

import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.db.DBMgr;
import com.graviton.lambda.calendar.model.*;

public class Initialize implements RequestHandler<Object, DataResponse>{
	public DataResponse handleRequest(Object obj, Context context) {

		DBMgr db = new DBMgr();

		ArrayList result = db.doSAC();

		return new DataResponse(result, 0, "OK");
	}
}
