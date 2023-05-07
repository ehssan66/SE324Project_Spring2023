package com.groupv.puzzles.Solution;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for handling Puzzle-related HTTP requests.
 */
@RestController()
public class SolutionController {
    

    /**
     * Parse the Solution string to Solution object
     *
     * @param solution the Solution in string representation
     * @return the Solution object
     * @throws ResponseStatusException if the Solution string is invalid
     */
    public Solution Parse(String solution) {
        return null;
        // TODO: implement my own parser
    }
}
