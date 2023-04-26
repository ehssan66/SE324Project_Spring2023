package com.ehssan.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        puzzle expected = new puzzle(0, "url", "solution");
        Mockito.when(mockDao.getPuzzleById(0)).thenReturn(expected);

        // Create a new PuzzleService using the mockDao
        puzzleService puzzleService = new puzzleService(mockDao);

        // Call getPuzzleById and verify that it returns the expected Puzzle object
        puzzle actual = puzzleService.getPuzzleById(0);
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
            Mockito.when(mockDao.getPuzzleById(i)).thenReturn(new puzzle(i, "url", "solution"));
        }

        // Create a new PuzzleService using the mockDao
        puzzleService puzzleService = new puzzleService(mockDao);

        // Call getRandomPuzzle 100 times and verify that it returns a Puzzle object with an id
        // between 0 and 4
        for (int i = 0; i < 100; i++) {
            puzzle puzzle = puzzleService.getRandomPuzzle();
            assertTrue(puzzle.getId() >= 0 && puzzle.getId() <= 4);
        }
    }

}

