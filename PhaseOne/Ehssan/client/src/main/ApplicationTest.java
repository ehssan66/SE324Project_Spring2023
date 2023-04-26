package com.ehssan.client;

// Import the necessary libraries and classes
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

// Define the test class
public class ApplicationTest {

    // Define the instance variable to be tested
    private ClientApplication application;

    // Initialize the instance variable before each test
    @Before
    public void setUp() {
        application = new ClientApplication();
    }

    /*
     * Test the contextLoads method to verify that the Spring application starts correctly.
     * Covers the contextLoads subdomain.
     */
    @Test
    public void contextLoads() {
        // Call the main method of the Spring application with no arguments
        ClientApplication.main(new String[] {});
    }

    /*
     * Test the getRandomPuzzle method to verify that a random puzzle can be fetched from the server.
     * Covers the getRandomPuzzle subdomain.
     */
    @Test
    public void testGetRandomPuzzle() throws Exception {
        // Get a random puzzle from the server
        puzzle puzzle = application.fetcher.getRandomPuzzle();

        // Make sure the puzzle URL is valid
        URL puzzleUrl = new URL(puzzle.getUrl());
        assertNotNull(puzzleUrl);

        // Make sure the solution URL is valid
        URL solutionUrl = new URL(puzzle.getSolution());
        assertNotNull(solutionUrl);
    }

    /*
     * Test the getPuzzleButton method to verify that the puzzle image is displayed and the "Show puzzle's solution" button is enabled.
     * Covers the getPuzzleButton subdomain.
     */
    @Test
    public void testGetPuzzleButton() throws Exception {
        // Click the "Get a random puzzle" button
        application.getPuzzleButton.doClick();

        // Make sure the puzzle image is displayed
        assertTrue(application.imageLabel.getIcon() != null);

        // Make sure the "Show puzzle's solution" button is enabled
        assertTrue(application.showSolutionButton.isEnabled());
    }

    /*
     * Test the showSolutionButton method to verify that the solution image is displayed.
     * Covers the showSolutionButton subdomain.
     */
    @Test
    public void testShowSolutionButton() throws Exception {
        // Click the "Get a random puzzle" button
        application.getPuzzleButton.doClick();

        // Click the "Show puzzle's solution" button
        application.showSolutionButton.doClick();

        // Make sure the solution image is displayed
        assertTrue(application.imageLabel.getIcon() != null);
    }

    /*
     * Test the initialButtonsState method to verify that the "Show puzzle's solution" button is disabled and the "Get a random puzzle" button is enabled initially.
     * Covers the initialButtonsState subdomain.
     */
    @Test
    public void testInitialButtonsState() throws Exception {
        // Make sure the "Show puzzle's solution" button is disabled initially
        assertFalse(application.showSolutionButton.isEnabled());

        // Make sure the "Get a random puzzle" button is enabled initially
        assertTrue(application.getPuzzleButton.isEnabled());
    }
}
