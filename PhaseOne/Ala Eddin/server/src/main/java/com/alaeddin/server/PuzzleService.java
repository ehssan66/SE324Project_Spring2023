package com.alaeddin.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PuzzleService {

    /**
     * PuzzleDao used to access the database
     */
    private final PuzzleDao puzzleDao;

    /**
     * Constructs a new PuzzleService object
     * 
     * @param puzzleDao PuzzleDao to be used within the new PuzzleService object
     */
    @Autowired
    public PuzzleService(@Qualifier("puzzleDao") PuzzleDao puzzleDao) {
        this.puzzleDao = puzzleDao;
    }

    /**
     * Returns a Puzzle object whose id is given.
     * 
     * @param id id of the Puzzle object to be returned
     * @return Puzzle object whose id is id
     */
    public Puzzle getPuzzleById(int id) {
        return puzzleDao.getPuzzleById(id);
    }

    /**
     * Returns a random Puzzle object
     * 
     * @return random Puzzle object
     */
    public Puzzle getRandomPuzzle() {
        int randomId =
                Math.toIntExact(Math.round(Math.random() * 100)) % puzzleDao.getNumberOfPuzzles();
        return getPuzzleById(randomId);
    }

}
