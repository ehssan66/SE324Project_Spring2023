package com.alaeddin.client;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class PuzzleTest {
    /*
     * Testing strategy
     * 
     * covers these partitions:
     *  partition on input values: valid input values, invalid input values
     *  partition on equality: equal, not equal, npt equal to null
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
        Puzzle puzzle = new Puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals(1, puzzle.getId());
    }
    
    // covers valid input values subdomain
    @Test
    public void testGetUrl() {
        Puzzle puzzle = new Puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals("http://puzzleimage.com", puzzle.getUrl());
    }
    
    // covers valid input values subdomain
    @Test
    public void testGetSolution() {
        Puzzle puzzle = new Puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals("http://solutionimage.com", puzzle.getSolution());
    }
    
    // covers invalid input values subdomain
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeId() {
        new Puzzle(-1, "http://puzzleimage.com", "http://solutionimage.com");
    }
    
    // covers invalid input values subdomain
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullUrl() {
        new Puzzle(1, null, "http://solutionimage.com");
    }
    
    // covers invalid input values subdomain
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullSolution() {
        new Puzzle(1, "http://puzzleimage.com", null);
    }

    // Partition on equality:

    // covers equality of two puzzles subdomain
    @Test
    public void testEquals() {
        Puzzle puzzle1 = new Puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        Puzzle puzzle2 = new Puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertEquals(puzzle1, puzzle2);
        assertEquals(puzzle1.hashCode(), puzzle2.hashCode());
    }
    
    // covers inequality of two puzzles subdomain
    @Test
    public void testNotEquals() {
        Puzzle puzzle1 = new Puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        Puzzle puzzle2 = new Puzzle(2, "http://puzzleimage.com", "http://solutionimage.com");
        assertNotEquals(puzzle1, puzzle2);
    }
    
    // covers null inequality of two puzzles subdomain
    @Test
    public void testNotEqualsNull() {
        Puzzle puzzle1 = new Puzzle(1, "http://puzzleimage.com", "http://solutionimage.com");
        assertNotEquals(puzzle1, null);
    }

}