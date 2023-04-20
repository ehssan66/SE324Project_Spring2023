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

    /*
     * Get the required image 
     * @param type to find, the image that required from client 
     * 
     * @return the path of required image or HttpStatus.NOT_FOUND
     */
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
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If the type parameter is not defined, return a 404 Not Found response
        }

        byte[] imageBytes = loadImageBytes(imagePath);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

    /*
     * @param use to represent the path to an image file.
     *  the file's content is loaded as a byte array.
     *  reads all of the file's bytes using the Files.the readAllBytes() function.
     * @return a byte array that represents the contents of the image file.
     */
    private byte[] loadImageBytes(String imagePath) throws IOException {
        // Load the puzzle image from the resources directory
        ClassPathResource resource = new ClassPathResource(imagePath);
        return Files.readAllBytes(resource.getFile().toPath());
    }
}