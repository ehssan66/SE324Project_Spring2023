package com.alaeddin.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class Fetcher {

    /**
     * url of the server API.
     */
    private final String url;

    /**
     * Puzzle object that is currently fetched, changed when a new Puzzle is requested.
     */
    private Puzzle puzzle;

    /**
     * Constructs a new Fetcher object.
     * 
     * @param url url of the server API
     */
    public Fetcher(String url) {
        this.url = url;
    }

    /**
     * chnages the Fetcher's stored Puzzle.
     * 
     * @param puzzle
     */
    private void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Returns a random Puzzle object.
     * 
     * @return random Puzzle object
     */
    public Puzzle getRandomPuzzle() {
        return fetchPuzzle();
    }

    /**
     * Fetches a new Puzzle from this object's url.
     * 
     * @return Puzzle object fetched from url
     */
    private Puzzle fetchPuzzle() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).thenApply(this::parse).join();
        return this.puzzle;
    }

    /**
     * Parses JSON data from response, stores it in a new Puzzle, and stores the new Puzzle in this
     * object's Puzzle.
     * 
     * @param response JSON data returned from HTTP request
     * @return Puzzle object from the parsed JSON data
     */
    private Puzzle parse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        int id = jsonObject.getInt("id");
        String puzzleUrl = jsonObject.getString("url");
        String solutionUrl = jsonObject.getString("solution");

        Puzzle puzzle = new Puzzle(id, puzzleUrl, solutionUrl);

        setPuzzle(puzzle);

        return puzzle;
    }
}
