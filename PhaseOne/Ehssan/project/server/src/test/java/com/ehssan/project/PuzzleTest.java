package com.ehssan.project;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    /*
     * Testing strategy
     * 
     * covers these partitions: 
     *  partition on input values: valid input values, invalid input values
     *  partition on equality: equal, not equal, not equal to null 
     * 
     * covers these subdomains:
     *  valid input values: get methods return the expected values for valid input
     *  invalid input values: constructor throws an IllegalArgumentException for invalid input 
     *  equality: Puzzle objects are equal iff they have the same attributes
     *  inequality: different Puzzle objects are not equal
     *  null inequality: Puzzle object is not equal to null
     */

    // Partition on input values:

    // covers valid input values subdomain
    @Test
    public void testGetId() {
        puzzle puzzle = new puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals(1, puzzle.getId());
    }

    // covers valid input values subdomain
    @Test
    public void testGetUrl() {
        puzzle puzzle = new puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals("http://puzzleimage.com", puzzle.getUrl());
    }

    // covers valid input values subdomain
    @Test
    public void testGetSolution() {
        puzzle puzzle = new puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals("http://solutionimage.com", puzzle.getSolution());
    }

    // covers invalid input values subdomain
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeId() {
        new puzzle(-1, "http://puzzleimage.com", "http://solutionimage.com");
    }

    // covers invalid input values subdomain
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullUrl() {
        new puzzle(1, null, "http://solutionimage.com");
    }

    // covers invalid input values subdomain
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullSolution() {
        new puzzle(1, "http://puzzleimage.com", null);
    }

    // Partition on equality:

    // covers equality of two puzzles subdomain
    @Test
    public void testEquals() {
        puzzle puzzle1 = new puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        puzzle puzzle2 = new puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals(puzzle1, puzzle2);
        assertEquals(puzzle1.hashCode(), puzzle2.hashCode());
    }

    // covers inequality of two puzzles subdomain
    @Test
    public void testNotEquals() {
        puzzle puzzle1 = new puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        puzzle puzzle2 = new puzzle(2, "http://puzzleimage.com", "http://solutionimage.com");
        assertNotEquals(puzzle1, puzzle2);
    }

    // covers null inequality of two puzzles subdomain
    @Test
    public void testNotEqualsNull() {
        puzzle puzzle1 = new puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertNotEquals(puzzle1, null);
    }

}
