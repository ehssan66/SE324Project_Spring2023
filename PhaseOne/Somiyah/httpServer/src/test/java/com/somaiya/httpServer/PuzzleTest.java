package com.somaiya.httpServer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PuzzleTest {

    private Puzzle puzzle;

    //Constructing Puzzle Object to test class methods
    @BeforeEach
    public void setUp() {
        puzzle = new Puzzle(1, "Suguru#1", "/empty/puzzle1", "/solved/puzzle1");
    }

    //Testing PuzzleNumber Getter method
    //Partition Test Equality
    @Test
    public void GetPuzzleNumberTest() {
        assertEquals(1, puzzle.getPuzzleNumber());
    }
    //Testing PuzzleNumber Setter method
    //Partition Test Equality
    @Test
    public void SetPuzzleNumberTest() {
        puzzle.setPuzzleNumber(2);
        assertEquals(2, puzzle.getPuzzleNumber());
    }
    //Testing PuzzleName Getter method
    //Partition Test Equality
    @Test
    public void GetPuzzleNameTest() {
        assertEquals("Suguru#1", puzzle.getPuzzleName());
    }
    //Testing PuzzleName Setter method
    //Partition Test Equality
    @Test
    public void SetPuzzleNameTest() {
        puzzle.setPuzzleName("Suguru#2");
        assertEquals("Suguru#2", puzzle.getPuzzleName());
    }
    //Testing PuzzlePath Getter method
    //Partition Test Equality
    @Test
    public void GetPuzzlePathTest() {
        assertEquals("/empty/puzzle1", puzzle.getPuzzlePath());
    }
    //Testing PuzzlePath Setter method
    //Partition Test Equality
    @Test
    public void SetPuzzlePathTest() {
        puzzle.setPuzzlePath("/empty/puzzle2");
        assertEquals("/empty/puzzle2", puzzle.getPuzzlePath());
    }
    //Testing SolutionPath Getter method
    //Partition Test Equality
    @Test
    public void GetSolutionPathTest() {
        assertEquals("/solved/puzzle1", puzzle.getSolutionPath());
    }
    //Testing SolutionPath Setter method
    //Partition Test Equality
    @Test
    public void SetSolutionPathTest() {
        puzzle.setSolutionPath("/solved/puzzle2");
        assertEquals("/solved/puzzle2", puzzle.getSolutionPath());
    }

}