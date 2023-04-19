package com.puzzle.puzzleapi.api.controller;

import com.puzzle.puzzleapi.service.PuzzleService;
import com.puzzle.puzzleapi.service.PuzzleService.PuzzleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PuzzleControllerTest {

    private PuzzleController puzzleController;
    private PuzzleService puzzleService;

    @BeforeEach
    public void setUp() {
        puzzleService = mock(PuzzleService.class);
        puzzleController = new PuzzleController(puzzleService);
    }

    @Test
    public void getSolvedPuzzle_Success() throws IOException {
        byte[] expectedImage = new byte[0];
        when(puzzleService.getPuzzleImage(PuzzleType.SOLVED_PUZZLE)).thenReturn(new ResponseEntity<>(expectedImage, HttpStatus.OK));

        ResponseEntity<byte[]> response = puzzleController.getSolvedPuzzle();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getSolvedPuzzle_InternalServerError() throws IOException {
        when(puzzleService.getPuzzleImage(PuzzleType.SOLVED_PUZZLE)).thenThrow(IOException.class);

        ResponseEntity<byte[]> response = puzzleController.getSolvedPuzzle();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getUnsolvedPuzzle_Success() throws IOException {
        byte[] expectedImage = new byte[0];
        when(puzzleService.getPuzzleImage(PuzzleType.UNSOLVED_PUZZLE)).thenReturn(new ResponseEntity<>(expectedImage, HttpStatus.OK));

        ResponseEntity<byte[]> response = puzzleController.getUnsolvedPuzzle();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUnsolvedPuzzle_InternalServerError() throws IOException {
        when(puzzleService.getPuzzleImage(PuzzleType.UNSOLVED_PUZZLE)).thenThrow(IOException.class);

        ResponseEntity<byte[]> response = puzzleController.getUnsolvedPuzzle();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}