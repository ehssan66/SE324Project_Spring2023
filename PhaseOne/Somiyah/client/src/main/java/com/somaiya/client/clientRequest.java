package com.somaiya.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class clientRequest {
    /**
     * Instantiating an HttpClient
     */
    HttpClient client;

    public void setApiUrl(String apiUrl) {
        this.API_URL = apiUrl;
    }

    /**
     * Defining final URL to request from the server
     */
    private  String API_URL = "http://localhost:8080/api/newPuzzle";

    /**
     * Connect and request URL from server
     * @return response to parse it
     * @throws IOException
     *@throws InterruptedException
     */
    public HttpResponse<String> ConnectToServer() throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(API_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
    /**
     * Parse JSON response objects
     * @return Puzzle object
     * @throws IOException
     *@throws InterruptedException
     */
    public Puzzle parser() throws IOException, InterruptedException {
        HttpResponse<String> response = ConnectToServer();
        ObjectMapper mapper = new ObjectMapper();
        Puzzle puzzle = mapper.readValue(response.body(), new TypeReference<Puzzle>() {
        });
        return puzzle;
    }
    /**
     * Call parser to create puzzle
     * @return Puzzle object
     * @throws IOException
     * @throws InterruptedException
     */
    public Puzzle requestPuzzle() throws IOException, InterruptedException {
        return parser();
    }
}
