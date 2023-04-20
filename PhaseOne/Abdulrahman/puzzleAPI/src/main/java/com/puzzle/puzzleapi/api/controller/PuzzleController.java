package com.puzzle.puzzleapi.api.controller;

import com.puzzle.puzzleapi.service.PuzzleService;
import com.puzzle.puzzleapi.service.PuzzleService.PuzzleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PuzzleController {

    private final PuzzleService puzzleService;

    public PuzzleController(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }
    /**
    * call puzzleService for SOLVED_PUZZLE image 
    * @return  SOLVED_PUZZLE image that required
    */
    // GET endpoint to retrieve the solved puzzle image
    @GetMapping("/Solved-Puzzle")
    public ResponseEntity<byte[]> getSolvedPuzzle() {
        try {
            // Call the getPuzzleImage method of the PuzzleService instance to return the solved puzzle image
            return puzzleService.getPuzzleImage(PuzzleType.SOLVED_PUZZLE);
        } catch (IOException e) {
            // If there is an IOException, return a 500 Internal Server Error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
    * call puzzleService for UNSOLVED_PUZZLE image 
    * @return  UNSOLVED_PUZZLE image that required
    */
    // GET endpoint to retrieve the unsolved puzzle image
    @GetMapping("/Unsolved-Puzzle")
    public ResponseEntity<byte[]> getUnsolvedPuzzle() {
        try {
            // Call the getPuzzleImage method of the PuzzleService instance to return the unsolved puzzle image
            return puzzleService.getPuzzleImage(PuzzleType.UNSOLVED_PUZZLE);
        } catch (IOException e) {
            // If there is an IOException, return a 500 Internal Server Error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}