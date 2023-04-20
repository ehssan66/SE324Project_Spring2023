package com.alaeddin.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

public class PuzzleServiceTest {
    /*
     * Testing strategy
     * 
     * covers these partitions:
     *  partition on function: getPuzzleById, getRandomPuzzle
     */


    // covers testGetPuzzleById partition
    @Test
    public void testGetPuzzleById() {
        // Create a mock PuzzleDao
        PuzzleDao mockDao = Mockito.mock(PuzzleDao.class);
        Puzzle expected = new Puzzle(0, "url", "solution");
        Mockito.when(mockDao.getPuzzleById(0)).thenReturn(expected);

        // Create a new PuzzleService using the mockDao
        PuzzleService puzzleService = new PuzzleService(mockDao);

        // Call getPuzzleById and verify that it returns the expected Puzzle object
        Puzzle actual = puzzleService.getPuzzleById(0);
        assertEquals(expected, actual);
    }

    // covers testGetNumberOfPuzzles partition
    @Test
    public void testGetRandomPuzzle() {
        // Create a mock PuzzleDao
        PuzzleDao mockDao = Mockito.mock(PuzzleDao.class);
        Mockito.when(mockDao.getNumberOfPuzzles()).thenReturn(5);

        for (int i = 0; i < mockDao.getNumberOfPuzzles(); i++) {
            // Return a Puzzle object with an id between 0 and 4
            Mockito.when(mockDao.getPuzzleById(i)).thenReturn(new Puzzle(i, "url", "solution"));
        }

        // Create a new PuzzleService using the mockDao
        PuzzleService puzzleService = new PuzzleService(mockDao);

        // Call getRandomPuzzle 100 times and verify that it returns a Puzzle object with an id
        // between 0 and 4
        for (int i = 0; i < 100; i++) {
            Puzzle puzzle = puzzleService.getRandomPuzzle();
            assertTrue(puzzle.getId() >= 0 && puzzle.getId() <= 4);
        }
    }

}

