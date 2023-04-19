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

    @GetMapping("/Solved-Puzzle")
    public ResponseEntity<byte[]> getSolvedPuzzle() {
        try {
            return puzzleService.getPuzzleImage(PuzzleType.SOLVED_PUZZLE);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Unsolved-Puzzle")
    public ResponseEntity<byte[]> getUnsolvedPuzzle() {
        try {
            return puzzleService.getPuzzleImage(PuzzleType.UNSOLVED_PUZZLE);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}