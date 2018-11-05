package com.graviton.lambda.calendar;

import java.io.*;
import java.util.stream.Collectors;

import com.graviton.lambda.calendar.model.*;
import org.json.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
/**
 * 
 */
public class CreateCalendar implements RequestStreamHandler {
    //@Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        //1. log 
    	context.getLogger().log("111111111111111111111111111111");
    	context.getLogger().log(new BufferedReader(new InputStreamReader(inputStream))
    	        .lines().collect(Collectors.joining(System.lineSeparator())));
    	context.getLogger().log("111111111122222222111111111111");
    	
    	//2. deal with input request
    	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    	JSONTokener parser = new JSONTokener(reader);
    	JSONObject responseJSON = new JSONObject();
    	
        JSONObject event = new JSONObject(parser);
        
        if (event.get("queryStringParameters") != null) {
        	JSONObject values = (JSONObject)event.get("queryStringParameters");
        	context.getLogger().log(values.toString());
        	 //logger.log(values);
        	 //	Calendar cld = new Calendar(values);
        	 // save calendar into database, now there is nothing
        }
        
        //3. generate output response
        Response response = new ResultResponse(true, new Status(0, "OK"));
        
        responseJSON.put("body", "response");
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJSON.toString());  
        writer.close();
    }
}
