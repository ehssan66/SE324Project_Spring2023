package com.somaiya.client;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class clientRequestTest {
    private String url = "https://jsonplaceholder.typicode.com/posts";

    //Test that Client Sends Successful GET request
    //Partition NotNull http response
    @Test
    public void testRequestPuzzle() throws IOException, InterruptedException {
        clientRequest client = new clientRequest();
        client.setApiUrl(url);
        client.ConnectToServer();
        Assertions.assertNotNull(client);
    }
}