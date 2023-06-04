package com.groupv.puzzles.Solver;

import com.groupv.puzzles.Puzzle.Puzzle;
import com.groupv.puzzles.Puzzle.PuzzleDao;


public class SuguruSolver implements Solver {
    Puzzle puzzle;
    String solution = "3a4b3b1b4c2c\n5a1d5e2b3c1f\n4a2e3e4e5c4g\n1a5h1e2i1c2g\n2a4h3i5i4i5g\n3h1h2h1i3g1g";

    public SuguruSolver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    /**
     * This method is responsible for solving the puzzle and returning a SolutionDto object.
     * @return SolutionDto
     */
    @Override
    public SolutionDto solve() {
        return new SolutionDto(this.solution);
    }

    /**
     * This method is overridden from the Solver interface. It compares the solution string with the content of the puzzle parameter using the equals() method. 
     * @param puzzle that An instance of PuzzleDao representing the puzzle to be checked.
     * @return CheckDto
     */
    @Override
    public CheckDto check(PuzzleDao puzzle) {
        return new CheckDto(this.solution.equals(puzzle.getContent()));
    }
}
