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

    public ResponseEntity<byte[]> getPuzzleImage(String type) throws IOException {
        byte[] imageBytes;

        if (type.equals("Solved_Puzzle")) {
            ClassPathResource resource = new ClassPathResource("static/solved_puzzle.jpg");
            imageBytes = Files.readAllBytes(resource.getFile().toPath());
        } else if (type.equals("Unsolved_Puzzle")) {
            ClassPathResource resource = new ClassPathResource("static/unsolved_puzzle.png");
            imageBytes = Files.readAllBytes(resource.getFile().toPath());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }
}
