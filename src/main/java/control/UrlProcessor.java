package control;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
}
