package view;

import control.UrlProcessor;
import model.ResponseDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HTTPGetRequestDetails {
    public static void main(String[] args) {
        List<String> urls;
        List<ResponseDetails> responses;
        Scanner kb;
        String input;

        kb = new Scanner(System.in);

        System.out.print("Enter path to the URL list file: ");
        input = kb.nextLine();

        urls = new ArrayList<>();
        responses = new ArrayList<>();

        try {
            urls = UrlProcessor.getUrlsFromFile(input);
        } catch (IOException e) {
            System.err.println("Invalid pathname.\n" + e.toString());
            System.exit(2);
        }

        for(String url : urls) {
            try {
                responses.add(UrlProcessor.makeGetRequest(url));
            } catch (IOException e) {
                System.err.println("An unexpected error has occurred with the URL " + url + "\n" + e.toString());
                System.exit(3);
            }
        }

        for(ResponseDetails r : responses) {
            System.out.println(r.toJsonString());
        }
    }
}
