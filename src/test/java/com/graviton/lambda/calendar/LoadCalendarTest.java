package com.graviton.lambda.calendar;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.graviton.lambda.calendar.model.DataResponse;
import com.graviton.lambda.calendar.model.SelectCalendar;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LoadCalendarTest {

    private static SelectCalendar input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
    	input = new SelectCalendar("personal");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLoadCalendar() {
        LoadCalendar handler = new LoadCalendar();
        Context ctx = createContext();

        DataResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        //Assert.assertEquals("Hello from Lambda!", output);
    }
}
