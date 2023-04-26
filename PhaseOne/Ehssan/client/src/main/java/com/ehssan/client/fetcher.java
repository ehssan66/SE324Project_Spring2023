package com.ehssan.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class fetcher {
    // url of the server API
    private final String url;

    // Puzzle object that is currently fetched, changed when a new Puzzle is requested.
    private puzzle puzzle;

    // Constructs a new Fetcher object with a given url
    public fetcher(String url) {
        this.url = url;
    }

    // changes the Fetcher's stored Puzzle
    private void setPuzzle(puzzle puzzle) {
        this.puzzle = puzzle;
    }

    // Returns a random Puzzle object.
    public puzzle getRandomPuzzle() {
        return fetchPuzzle();
    }

    // Fetches a new Puzzle from this object's url
    private puzzle fetchPuzzle() {
        // Create a new HttpClient instance
        HttpClient client = HttpClient.newHttpClient();
        // Build an HttpRequest with the given url
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        // Send the asynchronous HTTP request and handle the response
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::parse)
                .join();
        // Return the fetched Puzzle object
        return this.puzzle;
    }

    // Parses JSON data from response, stores it in a new Puzzle, and stores the new Puzzle in this object's Puzzle
    private puzzle parse(String response) {
        // Create a new JSONObject with the response data
        JSONObject jsonObject = new JSONObject(response);
        // Get the id, puzzleUrl, and solutionUrl from the JSON data
        int id = jsonObject.getInt("id");
        String puzzleUrl = jsonObject.getString("url");
        String solutionUrl = jsonObject.getString("solution");

        // Create a new Puzzle object with the extracted data
        puzzle puzzle = new puzzle(id, puzzleUrl, solutionUrl);

        // Set the new Puzzle object as the stored Puzzle object in this Fetcher object
        setPuzzle(puzzle);

        // Return the parsed Puzzle object
        return puzzle;
    }

}
