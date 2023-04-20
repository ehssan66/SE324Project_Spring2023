package com.alaeddin.client;

import org.junit.Test;

import static org.junit.Assert.*;

public class FetcherTest {
    /*
     * Testing strategy
     * 
     * covers these partitions:
     *  partition on function: getRandomPuzzle
     */

    private static final String TEST_URL = "http://localhost:8080/puzzle/random";

    // covers getRandomPuzzle
    @Test
    public void testGetRandomPuzzle() {
        Fetcher fetcher = new Fetcher(TEST_URL);
        Puzzle puzzle = fetcher.getRandomPuzzle();
        assertNotNull(puzzle);
        assertNotNull(puzzle.getId());
        assertNotNull(puzzle.getUrl());
        assertNotNull(puzzle.getSolution());
    }

}
