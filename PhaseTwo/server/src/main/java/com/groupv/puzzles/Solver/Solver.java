package com.groupv.puzzles.Solver;

import com.groupv.puzzles.Puzzle.PuzzleDao;

public interface Solver {
    public SolutionDto solve();

    public Boolean check(PuzzleDao puzzle);
}
