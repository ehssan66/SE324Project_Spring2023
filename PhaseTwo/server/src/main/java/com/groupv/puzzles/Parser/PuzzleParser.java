package com.groupv.puzzles.Parser;

import java.io.IOException;

import com.groupv.puzzles.Puzzle.Puzzle;

public interface PuzzleParser {
    Puzzle parse(String path) throws IOException;
}
