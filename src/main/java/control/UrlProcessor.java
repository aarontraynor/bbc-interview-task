package control;

import model.InvalidResponse;
import model.ResponseDetails;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

    public static ResponseDetails makeGetRequest(String urlStr) throws IOException {
        URL url;
        HttpURLConnection con;
        List<String> content;
        BufferedReader br;
        Calendar date;
        String line;
        int status;

        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            return new InvalidResponse(urlStr, "Invalid URL");
        }

        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        status = con.getResponseCode();

        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        content = new ArrayList<>();

        while((line = br.readLine()) != null) {
            content.add(line);
        }

        return null;
    }
}
