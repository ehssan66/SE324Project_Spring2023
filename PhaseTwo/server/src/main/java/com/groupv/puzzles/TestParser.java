package com.groupv.puzzles;

import com.groupv.puzzles.Parser.InkyParser;
import com.groupv.puzzles.Parser.PuzzleParser;
import com.groupv.puzzles.Parser.SuguruParser;
import com.groupv.puzzles.Puzzle.ParsedPuzzle;

public class TestParser {
    public static void main(String[] args) {

        PuzzleParser parser = new InkyParser();
		try {
			// file is in static/public
			ParsedPuzzle<Integer> puzzle = parser.parse("inky.txt");
			System.out.println(puzzle);
		} catch (Exception e) {
			e.printStackTrace();
		}

		parser = new SuguruParser();
		try {
			// file is in static/public
			ParsedPuzzle<String> puzzle = parser.parse("suguru.txt");
			System.out.println(puzzle);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
