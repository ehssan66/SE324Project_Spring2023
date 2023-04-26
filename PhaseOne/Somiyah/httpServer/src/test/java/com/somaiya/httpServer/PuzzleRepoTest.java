package com.somaiya.httpServer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class PuzzleRepoTest {
    private PuzzleRepo puzzleRepo;

    //Creating a Repository to use it on tests
    @BeforeEach
    public void setUp() {
        puzzleRepo = new PuzzleRepo();
        puzzleRepo.setPuzzleRepository(new Puzzle(1,"Suguru#1","/empty/puzzle1", "/solved/puzzle1"));
        puzzleRepo.setPuzzleRepository(new Puzzle(2,"Suguru#2","/empty/puzzle2", "/solved/puzzle2"));
        puzzleRepo.setPuzzleRepository(new Puzzle(3,"Suguru#3","/empty/puzzle3", "/solved/puzzle3"));
        puzzleRepo.setPuzzleRepository(new Puzzle(4,"Suguru#4","/empty/puzzle4", "/solved/puzzle4"));
        puzzleRepo.setPuzzleRepository(new Puzzle(5,"Suguru#5","/empty/puzzle5", "/solved/puzzle5"));

    }
    //Testing getting a puzzle by number
    // Partition valid puzzle number: within the Repo size, invalid puzzle number: greater than Repo size
    //Subdomain: Negative number
    //Partition Expected Name equals to constructed name, Expected Path equals to constructed Path
    @Test
    public void GetByPuzzleNoTest() {
        //Get Puzzle 1 from repository & Check it's not Null & Name is as constructed
        Puzzle puzzle1 = puzzleRepo.getByPuzzleNo(1);
        assertNotNull(puzzle1);
        assertEquals("Suguru#1", puzzle1.getPuzzleName());

        //Get Puzzle 2 from repository & Check it's not Null & Path is as constructed
        Puzzle puzzle2 = puzzleRepo.getByPuzzleNo(2);
        assertNotNull(puzzle2);
        assertEquals("/empty/puzzle2", puzzle2.getPuzzlePath());
        assertEquals("/solved/puzzle2", puzzle2.getSolutionPath());

        //Test getByPuzzleNo when out of repository size throw an Exception
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            puzzleRepo.getByPuzzleNo(0);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            puzzleRepo.getByPuzzleNo(8);
        });

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            puzzleRepo.getByPuzzleNo(-1);
        });
    }

    //Testing getting the repository size
    /* Partition true Repository size, false Repository size
       Subdomain Repository is not empty
     */
    @Test
    public void GetSizeTest() {
        PuzzleRepo puzzleRepo = new PuzzleRepo();
        Puzzle puzzle1 = new Puzzle(1,"Suguru#1","/empty/puzzle1", "/solved/puzzle1");
        Puzzle puzzle2 = new Puzzle(2,"Suguru#2","/empty/puzzle2", "/solved/puzzle2");
        Puzzle puzzle3 = new Puzzle(3,"Suguru#3","/empty/puzzle3", "/solved/puzzle3");
        puzzleRepo.setPuzzleRepository(puzzle1);
        puzzleRepo.setPuzzleRepository(puzzle2);
        puzzleRepo.setPuzzleRepository(puzzle3);
        int size = puzzleRepo.getSize();
        assertFalse(size == 5);
        assertNotNull(puzzleRepo.getSize());
        assertEquals(3, size);
    }
    //Testing adding puzzle to repository & check it is there
    /* Partition Repository contains object, Repository does not contain object
       Subdomain Repository is has no objects
     */
    @Test
    public void testContains() {
        PuzzleRepo puzzleRepo = new PuzzleRepo();
        Puzzle puzzle1 = new Puzzle(1,"Suguru#1","/empty/puzzle1", "/solved/puzzle1");
        puzzleRepo.setPuzzleRepository(puzzle1);
        assertTrue(puzzleRepo.RepoContains(puzzle1));

        Puzzle puzzle4 = new Puzzle(4,"Suguru#4","/empty/puzzle4", "/solved/puzzle4");
        assertFalse(puzzleRepo.RepoContains(puzzle4));

        assertFalse(puzzleRepo.RepoContains(null));
    }
}