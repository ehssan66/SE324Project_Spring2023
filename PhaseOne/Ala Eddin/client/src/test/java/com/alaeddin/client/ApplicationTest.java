package com.alaeddin.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
    /*
     * Testing strategy
     * 
     * covers these partitions:
     *  partition: function, buttons
     * 
     * covers these subdomains:
     *  function: contextLoads, getRandomPuzzle
     *  buttons: getPuzzleButton, showSolutionButton, initialButtonsState
     */

    private Application application;
    
    @Before
    public void setUp() {
        application = new Application();
    }

    // covers contextLoads subdomain
    @Test
    public void contextLoads() {
        // Verify that the Spring application starts correctly
        Application.main(new String[] {});
    }
    
    // covers getRandomPuzzle subdomain
    @Test
    public void testGetRandomPuzzle() throws Exception {
        // Get a random puzzle from the server
        Puzzle puzzle = application.fetcher.getRandomPuzzle();

        // Make sure the puzzle URL is valid
        URL puzzleUrl = new URL(puzzle.getUrl());
        assertNotNull(puzzleUrl);

        // Make sure the solution URL is valid
        URL solutionUrl = new URL(puzzle.getSolution());
        assertNotNull(solutionUrl);
    }

    // covers getPuzzleButton subdomain
    @Test
    public void testGetPuzzleButton() throws Exception {
        // Click the "Get a random puzzle" button
        application.getPuzzleButton.doClick();

        // Make sure the puzzle image is displayed
        assertTrue(application.imageLabel.getIcon() != null);

        // Make sure the "Show puzzle's solution" button is enabled
        assertTrue(application.showSolutionButton.isEnabled());
    }
    
    // covers showSolutionButton subdomain
    @Test
    public void testShowSolutionButton() throws Exception {
        // Click the "Get a random puzzle" button
        application.getPuzzleButton.doClick();

        // Click the "Show puzzle's solution" button
        application.showSolutionButton.doClick();

        // Make sure the solution image is displayed
        assertTrue(application.imageLabel.getIcon() != null);
    }

    // covers initialButtonsState subdomain
    @Test
    public void testInitialButtonsState() throws Exception {
        // Make sure the "Show puzzle's solution" button is disabled initially
        assertFalse(application.showSolutionButton.isEnabled());

        // Make sure the "Get a random puzzle" button is enabled initially
        assertTrue(application.getPuzzleButton.isEnabled());
    }
}
