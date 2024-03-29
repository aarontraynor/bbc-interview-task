import model.InvalidResponse;
import model.ValidResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ResponseDetailsTest {

    Long date;
    ValidResponse valid;
    InvalidResponse invalid;

    @Before
    public void setup() {
        date = System.currentTimeMillis();
        valid = new ValidResponse("https://www.bbc.co.uk/", 200, 100, date);
        invalid = new InvalidResponse("invalid://url", "Invalid URL");
    }

    @Test
    public void testAccessorMethodsOfValidResponse() {
        assertEquals("https://www.bbc.co.uk/", valid.getUrl());
        assertEquals(200, valid.getStatusCode());
        assertEquals(100, valid.getContentLength());
        assertEquals((long) date, valid.getDateOfRequest());
    }

    @Test
    public void testMutatorMethodsOfValidResponse() {
        valid.setUrl("https://www.google.co.uk");
        assertEquals("https://www.google.co.uk", valid.getUrl());

        valid.setStatusCode(404);
        assertEquals(404, valid.getStatusCode());

        valid.setContentLength(1000);
        assertEquals(1000, valid.getContentLength());

        Calendar newDate = Calendar.getInstance();
        valid.setDateOfRequest(10000);
        assertEquals(10000, valid.getDateOfRequest());
    }

    @Test
    public void testJsonRepresentationOfValidResponse() {
        assertEquals("{\n"
                + "\t\"Url\": \"" + "https://www.bbc.co.uk/" + "\",\n"
                + "\t\"Status_code\": " + 200 + ",\n"
                + "\t\"Content_length\": " + 100 + ",\n"
                + "\t\"Date\": " + "\"" + valid.getDateOfRequestAsString() + "\"\n"
                + "}", valid.toJsonString());
    }

    @Test
    public void testAccessorMethodsOfInvalidResponse() {
        assertEquals("invalid://url", invalid.getUrl());
        assertEquals("Invalid URL", invalid.getError());
    }

    @Test
    public void testMutatorMethodsOfInvalidResponse() {
        invalid.setUrl("another://invalid.address/");
        assertEquals("another://invalid.address/", invalid.getUrl());

        invalid.setError("page timed out");
        assertEquals("page timed out", invalid.getError());
    }

    @Test
    public void testJsonRepresentationOfInvalidResponse() {
        assertEquals("{\n"
                + "\t\"Url\": \"" + "invalid://url" + "\",\n"
                + "\t\"Error\": " + "\"" + "Invalid URL" + "\"\n"
                + "}", invalid.toJsonString());
    }
}
