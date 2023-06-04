package com.groupv.puzzles.Solver;

import com.groupv.puzzles.Puzzle.Puzzle;

public class SolverFactory {

    /**
     * This method creates and returns an appropriate solver based on the type of puzzle.
     * @param puzzle that An instance of Puzzle representing the puzzle for which a solver needs to be created
     * @return Solver
     */
    public static Solver create(Puzzle puzzle) {
        System.out.println("SolverFactory: " + puzzle.getType().getName());
        return switch (puzzle.getType().getName()) {
            case "suguru" -> new SuguruSolver(puzzle);
            case "inkies" -> new InkeisSolver(puzzle);
        
            default -> null;
        };
    }
}
