package com.graviton.lambda.calendar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.graviton.lambda.calendar.model.*;

public class DeleteCalendar implements RequestHandler<SelectCalendar, ResultResponse> {

    @Override
    public ResultResponse handleRequest(SelectCalendar cld, Context context) {
        return new ResultResponse(true, 0, "OK");
    }
}
