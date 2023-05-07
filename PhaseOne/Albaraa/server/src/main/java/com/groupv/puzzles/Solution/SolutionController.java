package com.groupv.puzzles.Solution;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.groupv.puzzles.Puzzle.Puzzle;

/**
 * Controller for handling Puzzle-related HTTP requests.
 */
@RestController()
public class SolutionController {
    

    /**
     * Parse the Puzzle string to Puzzle object
     *
     * @param puzzle the Puzzle in string representation
     * @return the Puzzle object
     * @throws ResponseStatusException if the Puzzle string is invalid
     */
    public Puzzle Parse(String puzzle) {
        return null;
        // TODO: implement my own parser
    }
}
