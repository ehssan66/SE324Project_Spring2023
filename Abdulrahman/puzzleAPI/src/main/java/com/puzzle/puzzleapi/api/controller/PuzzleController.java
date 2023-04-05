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

    @GetMapping("/Solved-Puzzle")
    public ResponseEntity<byte[]> SolvedPuzzle() {
        try {
            return puzzleService.getPuzzleImage("Solved_Puzzle");
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Unsolved-Puzzle")
    public ResponseEntity<byte[]> UnsolvedPuzzle() {
        try {
            return puzzleService.getPuzzleImage("Unsolved_Puzzle");
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
