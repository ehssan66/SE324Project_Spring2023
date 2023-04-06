package com.puzzle.puzzleapi.api.controller;


import com.puzzle.puzzleapi.service.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PuzzleController {
    
    @Autowired
    private PuzzleService puzzleService;

    // GET endpoint to retrieve the solved puzzle image
    @GetMapping("/Solved-Puzzle")
    public ResponseEntity<byte[]> SolvedPuzzle() {
        try {
            // Call the getPuzzleImage method of the PuzzleService instance to return the solved puzzle image
            return puzzleService.getPuzzleImage("Solved_Puzzle");
        } catch (IOException e) {
            // If there is an IOException, return a 500 Internal Server Error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET endpoint to retrieve the unsolved puzzle image
    @GetMapping("/Unsolved-Puzzle")
    public ResponseEntity<byte[]> UnsolvedPuzzle() {
        try {
            // Call the getPuzzleImage method of the PuzzleService instance to return the unsolved puzzle image
            return puzzleService.getPuzzleImage("Unsolved_Puzzle");
        } catch (IOException e) {
            // If there is an IOException, return a 500 Internal Server Error response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
