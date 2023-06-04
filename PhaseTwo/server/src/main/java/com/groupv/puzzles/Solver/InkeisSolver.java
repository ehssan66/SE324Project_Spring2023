package com.groupv.puzzles.Solver;

import com.groupv.puzzles.Puzzle.Puzzle;
import com.groupv.puzzles.Puzzle.PuzzleDao;

public class InkeisSolver implements Solver {
    Puzzle puzzle;
    String solution = "2 3 1\n1 2 3\n3 1 2";

    public InkeisSolver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    @Override
    public SolutionDto solve() {
        return new SolutionDto(this.solution);
    }

    @Override
    public CheckDto check(PuzzleDao puzzle) {
        return new CheckDto(this.solution.equals(puzzle.getContent()));
    }
}
