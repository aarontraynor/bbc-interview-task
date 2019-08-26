package control;

import model.InvalidResponse;
import model.ResponseDetails;
import model.ValidResponse;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Static methods for processing data in the program
 */
public class UrlProcessor {
    /**
     * Read URLs from file and return them in a list
     *
     * @param filepath the path at which the file containing URLs can be found
     * @return a list of URLs from the file specified
     * @throws IOException an IOException is thrown if the file does not exist, or if an error occurs while reading the file
     */
    public static List<String> getUrlsFromFile(String filepath) throws IOException {
        // Declare variables for method
        List<String> urls;
        BufferedReader br;
        String nextLine;

        // Initialise list
        urls = new ArrayList<>();

        // Initialise BufferedReader for the file at the path provided
        br = new BufferedReader(new FileReader(new File(filepath)));

        // For each line in the file, add that line as a new string in the urls list
        while ((nextLine = br.readLine()) != null) {
            urls.add(nextLine);
        }

        // Return the list of URLs
        return urls;
    }

    /**
     * Make a get request to a given URL and return the details of the response
     *
     * @param urlStr the URL to which the request should be made
     * @return the response details from the URL request
     * @throws IOException
     */
    public static ResponseDetails makeGetRequest(String urlStr) throws IOException {
        URL url;
        HttpURLConnection con;
        List<String> content;
        BufferedReader br;
        Long date;
        String line;
        int status;

        // Make initial request or return error if URL is invalid
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            return new InvalidResponse(urlStr, "Invalid URL");
        }

        // Initialise connection
        con = (HttpURLConnection) url.openConnection();

        // Set request type to GET request
        con.setRequestMethod("GET");
        con.setReadTimeout(10000);
        con.setConnectTimeout(10000);

        // Return response details as a ValidResponse object or return InvalidResponse if connection times out.
        try {
            return new ValidResponse(urlStr, con.getResponseCode(), con.getContentLength(), con.getDate());
        } catch (SocketTimeoutException e) {
            return new InvalidResponse(urlStr, "Connection timed out");
        }
    }
}
