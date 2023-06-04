package com.groupv.puzzles.Solver;

import com.groupv.puzzles.Puzzle.Puzzle;

public class SolverFactory {

    public static Solver create(Puzzle puzzle) {
        System.out.println("SolverFactory: " + puzzle.getType().getName());
        return switch (puzzle.getType().getName()) {
            case "suguru" -> new SuguruSolver(puzzle);
            case "inkies" -> new InkeisSolver(puzzle);
        
            default -> null;
        };
    }
}
