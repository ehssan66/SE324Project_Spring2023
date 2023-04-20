package com.puzzle.puzzleapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;

import static com.puzzle.puzzleapi.service.PuzzleService.PuzzleType.*;
import static org.junit.jupiter.api.Assertions.*;

public class PuzzleServiceTest {

    private PuzzleService puzzleService;

    @BeforeEach
    public void setUp() {
        puzzleService = new PuzzleService();
    }

    @Test
    public void getPuzzleImage_SolvedPuzzle() throws IOException {
        byte[] expectedImage = Files.readAllBytes(new ClassPathResource("static/solved_puzzle.jpg").getFile().toPath());
        ResponseEntity<byte[]> response = puzzleService.getPuzzleImage(SOLVED_PUZZLE);
        assertArrayEquals(expectedImage, response.getBody());
    }

    @Test
    public void getPuzzleImage_UnsolvedPuzzle() throws IOException {
        byte[] expectedImage = Files.readAllBytes(new ClassPathResource("static/unsolved_puzzle.png").getFile().toPath());
        ResponseEntity<byte[]> response = puzzleService.getPuzzleImage(UNSOLVED_PUZZLE);
        assertArrayEquals(expectedImage, response.getBody());
    }
}