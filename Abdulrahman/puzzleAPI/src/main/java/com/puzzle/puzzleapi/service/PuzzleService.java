package com.puzzle.puzzleapi.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class PuzzleService {

    public enum PuzzleType {
        SOLVED_PUZZLE,
        UNSOLVED_PUZZLE
    }

    public ResponseEntity<byte[]> getPuzzleImage(PuzzleType type) throws IOException {
        String imagePath;

        switch (type) {
            case SOLVED_PUZZLE:
                imagePath = "static/solved_puzzle.jpg";
                break;
            case UNSOLVED_PUZZLE:
                imagePath = "static/unsolved_puzzle.png";
                break;
            default:
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] imageBytes = loadImageBytes(imagePath);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    private byte[] loadImageBytes(String imagePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(imagePath);
        return Files.readAllBytes(resource.getFile().toPath());
    }
}