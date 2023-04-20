package com.alaeddin.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("puzzle")
@RestController
public class PuzzleController {

    /**
     * PuzzleService that provides services
     */
    private final PuzzleService puzzleService;

    /**
     * Constructs a new PuzzleController object
     * 
     * @param puzzleService PuzzleService to be used within the new PuzzleController object
     */
    @Autowired
    public PuzzleController(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }

    /**
     * Returns a random Puzzle object
     * 
     * @return random Puzzle object
     */
    @GetMapping(path = {"random"})
    public Puzzle getRandomPuzzle() {
        return puzzleService.getRandomPuzzle();
    }

}
