package com.groupv.puzzles.Solver;

import com.groupv.puzzles.Puzzle.PuzzleDao;

public interface Solver {
    public SolutionDto solve();

    public CheckDto check(PuzzleDao puzzle);
}
