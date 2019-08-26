package model;

import java.util.Date;

/**
 * Parent object to represent the details of a response from a URL.
 * <p>
 * Its sub-types are ValidResponse or InvalidResponse
 */
public abstract class ResponseDetails {
    private String url;

    /**
     * ResponseDetails Constructor
     *
     * @param url the URL used to obtain the response
     */
    protected ResponseDetails(String url) {
        this.url = url;
    }

    /**
     * Accessor method for the url state
     *
     * @return the URL used to obtain the response
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Mutator method for the url state
     *
     * @param url the URL used to obtain the response
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public abstract String toJsonString();
}
