package com.groupv.puzzles.Puzzle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import com.groupv.puzzles.PuzzleType.PuzzleTypeRepository;
import com.groupv.puzzles.Solver.SolutionDto;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.http.MediaType;
import com.groupv.puzzles.Solver.CheckDto;


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

        List<Puzzle> all = puzzleRepository.findByType(puzzleType)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No puzzle type found"));
        int max =  all.size();
        int index = (int) Math.random() * (max - 1) + 1;

        Puzzle puzzle = all.get(index - 1);

        return new RedirectView("/api/puzzles/" + puzzle.getId());
    }

    /**
     * Gets a Puzzle by its ID.
     *
     * @param id the ID of the Puzzle to get
     * @return the Puzzle with the given ID
     * @throws ResponseStatusException if no Puzzle is found
     */
    @GetMapping("/api/puzzles/{id}/solve")
    public SolutionDto solvePuzzle(@PathVariable("id") Long id) {
        Puzzle puzzle = puzzleRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No puzzle found"));

        return puzzle.solver().solve();
    }

    /**
     * Checks a Puzzle's solution.
     *
     * @param id        the ID of the Puzzle to check
     * @param puzzleDao the PuzzleDao containing the solution to check
     * @return a CheckDto containing the results of the check
     * @throws ResponseStatusException if no Puzzle is found
     */
    @PostMapping(value= "/api/puzzles/{id}/check", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CheckDto checkPuzzle(@PathVariable("id") Long id, @RequestBody PuzzleDao puzzleDao) {
        Puzzle puzzle = puzzleRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No puzzle found"));

        return puzzle.solver().check(puzzleDao);
    }
}
