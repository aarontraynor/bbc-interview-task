package view;

import control.UrlProcessor;
import model.ResponseDetails;
import model.ValidResponse;

import java.io.IOException;
import java.util.*;

/**
 * Main view class for using the program
 */
public class HTTPGetRequestDetails {
    /**
     * Main method executes the program
     *
     * @param args arguments from the command line (unused by this program - any arguments given will be ignored
     */
    public static void main(String[] args) {
        // Declare variables
        List<String> urls;
        Map<Integer, List<ValidResponse>> responsesByStatus;
        Scanner kb;
        String input;

        // Initialise input scanner
        kb = new Scanner(System.in);

        // Get file path
        System.out.print("Enter path to the URL list file: ");
        input = kb.nextLine();

        // Initialise arrays
        urls = new ArrayList<>();
        responsesByStatus = new HashMap<>();

        System.out.println("\n\n*** RESPONSES ***\n");

        // Read file and exit with error if file is not found.
        try {
            urls = UrlProcessor.getUrlsFromFile(input);
        } catch (IOException e) {
            System.err.println("Invalid pathname.\n" + e.toString());
            System.exit(2);
        }

        // Make GET requests for URLs and log responses. Exit with error if an exception is thrown.
        for (String url : urls) {
            try {
                // Get response from url
                ResponseDetails response = UrlProcessor.makeGetRequest(url);

                // Print response details
                System.out.println(response.toJsonString());

                // Add to summary map if a ValidResponse was received
                if(response instanceof ValidResponse) {
                    // Get status code
                    int statusCode = ((ValidResponse) response).getStatusCode();

                    // Initialise new array in the map for statusCode if it doesn't already exist
                    if(!responsesByStatus.containsKey(statusCode)) {
                        responsesByStatus.put(statusCode, new ArrayList<>());
                    }

                    // Add the response to the array mapped to the statusCode
                    responsesByStatus.get(statusCode).add((ValidResponse) response);
                }

            } catch (IOException e) {
                System.err.println("An unexpected error has occurred with the URL " + url + "\n" + e.toString());
                System.exit(3);
            }
        }

        System.out.println("\n\n*** SUMMARY OF RESULTS ***\n");

        // Print summary JSON for each status code
        for(int statusCode : responsesByStatus.keySet()) {
            System.out.println("{\n"
                    + "\t\"Status_code\": \"" + statusCode + "\",\n"
                    + "\t\"Number_of_responses\": " + responsesByStatus.get(statusCode).size() + "\n"
                    + "}");
        }
    }
}
