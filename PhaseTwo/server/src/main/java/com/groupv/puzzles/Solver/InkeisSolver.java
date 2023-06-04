package com.groupv.puzzles.Solver;

import com.groupv.puzzles.Puzzle.Puzzle;
import com.groupv.puzzles.Puzzle.PuzzleDao;

public class InkeisSolver implements Solver {
    Puzzle puzzle;
    String solution = "InkeisSolver";

    public InkeisSolver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    @Override
    public SolutionDto solve() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean check(PuzzleDao puzzle) {
        // TODO Auto-generated method stub
        return true;
    }
}
