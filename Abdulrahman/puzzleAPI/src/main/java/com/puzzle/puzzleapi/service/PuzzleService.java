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

    // Method to return a puzzle image based on its type (Solved_Puzzle or Unsolved_Puzzle)
    public ResponseEntity<byte[]> getPuzzleImage(String type) throws IOException {
        byte[] imageBytes;

        // Check the type parameter to determine which image to return
        if (type.equals("Solved_Puzzle")) {

            // Load the solved puzzle image from the resources directory
            ClassPathResource resource = new ClassPathResource("static/solved_puzzle.jpg");
            imageBytes = Files.readAllBytes(resource.getFile().toPath());
        } else if (type.equals("Unsolved_Puzzle")) {

            // Load the unsolved puzzle image from the resources directory
            ClassPathResource resource = new ClassPathResource("static/unsolved_puzzle.png");
            imageBytes = Files.readAllBytes(resource.getFile().toPath());
        } else {
            // If the type parameter is not defined, return a 404 Not Found response
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Return the puzzle image as a byte array with the appropriate content type
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }
}
