package com.groupv.puzzles.Parser;

import java.io.IOException;
import java.net.URISyntaxException;

import com.groupv.puzzles.Puzzle.ParsedPuzzle;

public interface PuzzleParser {
    ParsedPuzzle parse(String fileName) throws IOException, URISyntaxException;
}
