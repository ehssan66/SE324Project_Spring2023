package com.ehssan.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("puzzle")
@RestController
public class serverController {

    /**
     * PuzzleService that provides services
     */
    private final puzzleService puzzleService;

    /**
     * Constructs a new PuzzleController object
     * 
     * @param puzzleService PuzzleService to be used within the new PuzzleController object
     */
    @Autowired
    public serverController(puzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }

    /**
     * Returns a random Puzzle object
     * 
     * @return random Puzzle object
     */
    @GetMapping(path = {"random"})
    public puzzle getRandomPuzzle() {
        return puzzleService.getRandomPuzzle();
    }

}
