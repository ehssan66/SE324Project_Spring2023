package com.groupv.puzzles.Puzzle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import com.groupv.puzzles.PuzzleType.PuzzleTypeRepository;

/**
 * Controller for handling Puzzle-related HTTP requests.
 */
@RestController()
public class PuzzleController {

    private final PuzzleRepository puzzleRepository;
    private final PuzzleTypeRepository puzzleTypeRepository;

    /**
     * Constructs a new PuzzleController with the given repositories.
     *
     * @param puzzleRepository     the repository for Puzzle entities
     * @param puzzleTypeRepository the repository for PuzzleType entities
     */
    public PuzzleController(PuzzleRepository puzzleRepository, PuzzleTypeRepository puzzleTypeRepository) {
        this.puzzleRepository = puzzleRepository;
        this.puzzleTypeRepository = puzzleTypeRepository;
    }

    /**
     * Gets a random Puzzle of the specified type and redirects to its endpoint.
     *
     * @param name the name of the PuzzleType to get a random Puzzle from
     * @return a RedirectView to the endpoint of the random Puzzle
     * @throws ResponseStatusException if no PuzzleType or Puzzles are found
     */
    @GetMapping("/api/puzzle-types/{name}/puzzles/random")
    public RedirectView getRandomPuzzle(@PathVariable("name") String name) {
        PuzzleType puzzleType = puzzleTypeRepository.findByName(name)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No puzzle type found"));

        long max = puzzleRepository.countByType(puzzleType);
        long index = (long) Math.random() * (max - 1) + 1;

        Puzzle puzzle = puzzleRepository.findById(index)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No puzzle found"));

        return new RedirectView("/api/puzzles/" + puzzle.getId());
    }

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
