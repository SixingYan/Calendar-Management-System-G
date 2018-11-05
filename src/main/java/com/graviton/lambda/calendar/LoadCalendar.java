package com.graviton.lambda.calendar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.model.*;

public class LoadCalendar implements RequestHandler<SelectCalendar, GetCalendar> {

    //@Override
    public GetCalendar handleRequest(SelectCalendar input, Context context) {
        context.getLogger().log("Input: " + input);
 
        // TODO: implement your handler
        String greetingString = String.format("The calendar name is %s.", input.name);
        return new GetCalendar(greetingString);
    }

}
