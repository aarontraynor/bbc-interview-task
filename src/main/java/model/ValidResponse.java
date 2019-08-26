package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Object to represent the details of a valid response from a URL.
 *
 */
public class ValidResponse extends ResponseDetails {
    private int statusCode;
    private int contentLength;
    private long dateOfRequest;

    /**
     * ValidResponse Constructor
     *
     * @param url the URL to which the request was made
     * @param statusCode the status code returned by the URL
     * @param contentLength the number of lines in the response received
     * @param dateOfRequest the date and time at which the request was made
     */
    public ValidResponse(String url, int statusCode, int contentLength, long dateOfRequest) {
        super(url);
        this.statusCode = statusCode;
        this.contentLength = contentLength;
        this.dateOfRequest = dateOfRequest;
    }

    /**
     * Accessor method for the statusCode state
     *
     * @return the HTTP status code of the response
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Mutator method for the statusCode state
     *
     * @param statusCode the HTTP status code of the response
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Accessor method for the contentLength state
     *
     * @return the number of lines contained within the response
     */
    public int getContentLength() {
        return contentLength;
    }

    /**
     * Mutator method for the contentLength state
     *
     * @param contentLength the number of lines contained within the response
     */
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    /**
     * Accessor method for the dateOfRequest state
     *
     * @return the date and time in milliseconds from Epoch at which the response was sent
     */
    public long getDateOfRequest() {
        return dateOfRequest;
    }

    /**
     * Accessor method for the dateOfRequest state in a human-readable format
     *
     * @return the date and time in a human-readable format at which the response was sent
     */
    public String getDateOfRequestAsString() {
        SimpleDateFormat df = new SimpleDateFormat("EEE, DD MMM yyyy HH:mm:ss z");

        return df.format(dateOfRequest);
    }

    /**
     * Mutator method for the dateOfRequest state
     *
     * @param dateOfRequest the date and time at which the response was sent
     */
    public void setDateOfRequest(long dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    /**
     * Create a JSON representation of the object
     *
     * @return the object as JSON
     */
    public String toJsonString() {

        return  "{\n"
                + "\t\"Url\": \"" + super.getUrl() + "\",\n"
                + "\t\"Status_code\": " + this.statusCode + ",\n"
                + "\t\"Content_length\": " + this.contentLength + ",\n"
                + "\t\"Date\": " + "\"" + getDateOfRequestAsString() + "\"\n"
                + "}";
    }
}
