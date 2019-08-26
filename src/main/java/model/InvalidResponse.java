package model;


/**
 * Object to represent the details of an invalid response from a URL.
 */
public class InvalidResponse extends ResponseDetails {
    String error;

    /**
     * InvalidResponse Constructor
     *
     * @param url
     * @param error
     */
    public InvalidResponse(String url, String error) {
        super(url);
        this.error = error;
    }

    /**
     * Accessor method for the error state
     *
     * @return the error message for the invalid response
     */
    public String getError() {
        return error;
    }

    /**
     * Mutator method for the error state
     *
     * @param error the error message for the invalid response
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Create a JSON representation of the object
     *
     * @return the object as JSON
     */
    public String toJsonString() {
        return "{\n"
                + "\t\"Url\": \"" + super.getUrl() + "\",\n"
                + "\t\"Error\": " + "\"" + this.getError() + "\""
                + "}";
    }
}
