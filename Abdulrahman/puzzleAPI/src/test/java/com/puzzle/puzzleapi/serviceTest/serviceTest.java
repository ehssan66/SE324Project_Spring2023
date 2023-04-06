package com.puzzle.puzzleapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PuzzleServiceTest {

    @InjectMocks
    private PuzzleService puzzleService;

    @Test
    public void testGetSolvedPuzzleImage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/solved_puzzle.jpg");
        byte[] expectedBytes = Files.readAllBytes(resource.getFile().toPath());

        ResponseEntity<byte[]> response = puzzleService.getPuzzleImage("Solved_Puzzle");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_JPEG, response.getHeaders().getContentType());
        assertArrayEquals(expectedBytes, response.getBody());
    }

    @Test
    public void testGetUnsolvedPuzzleImage() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/unsolved_puzzle.png");
        byte[] expectedBytes = Files.readAllBytes(resource.getFile().toPath());

        ResponseEntity<byte[]> response = puzzleService.getPuzzleImage("Unsolved_Puzzle");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        assertArrayEquals(expectedBytes, response.getBody());
    }

    @Test
    public void testGetUnknownPuzzleImage() throws IOException {
        ResponseEntity<byte[]> response = puzzleService.getPuzzleImage("Unknown_Puzzle");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
