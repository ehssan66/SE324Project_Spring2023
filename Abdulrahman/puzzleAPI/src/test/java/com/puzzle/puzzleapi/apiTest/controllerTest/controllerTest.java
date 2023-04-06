package com.puzzle.puzzleapi.api.controller;

import com.puzzle.puzzleapi.service.PuzzleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PuzzleControllerTest {

    @Mock
    private PuzzleService puzzleService;

    @InjectMocks
    private PuzzleController puzzleController;

    @Test
    public void testSolvedPuzzle() throws IOException {
        byte[] mockImageData = new byte[10];
        when(puzzleService.getPuzzleImage(anyString())).thenReturn(new ResponseEntity<>(mockImageData, HttpStatus.OK));

        ResponseEntity<byte[]> response = puzzleController.SolvedPuzzle();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUnsolvedPuzzle() throws IOException {
        byte[] mockImageData = new byte[10];
        when(puzzleService.getPuzzleImage(anyString())).thenReturn(new ResponseEntity<>(mockImageData, HttpStatus.OK));

        ResponseEntity<byte[]> response = puzzleController.UnsolvedPuzzle();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
