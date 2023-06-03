package com.groupv.puzzles;

import java.net.URI;

import com.groupv.puzzles.Parser.InkyParser;
import com.groupv.puzzles.Parser.PuzzleParser;
import com.groupv.puzzles.Puzzle.Puzzle;

public class TestParser {
    public static void main(String[] args) {
        PuzzleParser parser = new InkyParser();
		try {
            // get path from static resources
            URI uri = new URI(TestParser.class.getClassLoader().getResource("static/public/puzzle.txt").toString());
            String path = uri.getPath();
			Puzzle puzzle = parser.parse(path);
			System.out.println(puzzle);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
