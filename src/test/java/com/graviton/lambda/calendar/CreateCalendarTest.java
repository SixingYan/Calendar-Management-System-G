package com.graviton.lambda.calendar;

import java.io.IOException;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.graviton.lambda.calendar.model.*;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateCalendarTest {

    private static Calendar input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new Calendar("personal", 30, 1000, 1700, 20181000, 20181200);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testCreateCalendar() {
        CreateCalendar handler = new CreateCalendar();
        Context ctx = createContext();

        ResultResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(0, output.code);
    }
}
