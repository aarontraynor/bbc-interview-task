package view;

import control.UrlProcessor;
import model.ResponseDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        // Print the response details of each URL
        for (ResponseDetails r : responses) {
            System.out.println(r.toJsonString());
        }
    }
}
