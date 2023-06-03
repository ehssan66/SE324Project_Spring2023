package com.groupv.puzzles;

import com.groupv.puzzles.Parser.InkyParser;
import com.groupv.puzzles.Parser.PuzzleParser;
import com.groupv.puzzles.Puzzle.ParsedPuzzle;

public class TestParser {
    public static void main(String[] args) {
        PuzzleParser parser = new InkyParser();
		try {
			ParsedPuzzle puzzle = parser.parse("puzzle.txt");
			System.out.println(puzzle);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
