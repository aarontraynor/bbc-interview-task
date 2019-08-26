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
        List<ResponseDetails> responses;
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
        responses = new ArrayList<>();
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
                responses.add(UrlProcessor.makeGetRequest(url));
            } catch (IOException e) {
                System.err.println("An unexpected error has occurred with the URL " + url + "\n" + e.toString());
                System.exit(3);
            }
        }

        // Print the response details of each URL and map to relevant status code if it is a valid response
        for (ResponseDetails r : responses) {
            // Print to console
            System.out.println(r.toJsonString());

            // Add to map
            if(r instanceof ValidResponse) {
                // Get status code
                int statusCode = ((ValidResponse) r).getStatusCode();

                // Initialise new array in the map for statusCode if it doesn't already exist
                if(!responsesByStatus.containsKey(statusCode)) {
                    responsesByStatus.put(statusCode, new ArrayList<>());
                }

                // Add the response to the array mapped to the statusCode
                responsesByStatus.get(statusCode).add((ValidResponse) r);
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
