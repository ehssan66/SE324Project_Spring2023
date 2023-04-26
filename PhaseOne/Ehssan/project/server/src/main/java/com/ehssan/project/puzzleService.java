package com.ehssan.project;


// Import necessary Spring Framework classes and annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// Service annotation to indicate that this class is a service component
@Service

public class puzzleService {
        // PuzzleDao object used to access the database
    private final PuzzleDao puzzleDao;

    // Constructor-based dependency injection using Autowired and Qualifier annotations
    @Autowired
    public puzzleService(@Qualifier("puzzleDao") PuzzleDao puzzleDao) {
        this.puzzleDao = puzzleDao;
    }

    /**
     * Retrieves a Puzzle object from the database based on its id.
     * 
     * @param id the id of the Puzzle to retrieve
     * @return the Puzzle object with the specified id
     */
    public puzzle getPuzzleById(int id) {
        return puzzleDao.getPuzzleById(id);
    }

    /**
     * Retrieves a random Puzzle object from the database.
     * 
     * @return a random Puzzle object
     */
    public puzzle getRandomPuzzle() {
        // Generate a random id between 0 and the number of puzzles in the database
        int randomId = Math.toIntExact(Math.round(Math.random() * 100)) % puzzleDao.getNumberOfPuzzles();
        // Retrieve the Puzzle object with the generated id
        return getPuzzleById(randomId);
    }

}