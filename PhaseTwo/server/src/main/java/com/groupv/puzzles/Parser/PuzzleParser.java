package com.groupv.puzzles.Parser;

import java.io.IOException;
import java.net.URISyntaxException;

import com.groupv.puzzles.Puzzle.ParsedPuzzle;

public interface PuzzleParser {
    /**
     * Parses the given file into a Puzzle object.
     *
     * @param fileName containing puzzle to parse
     * @return Puzzle object
     * @throws IOException if the file cannot be read
     * @throws URISyntaxException if the file path is invalid
     */
    ParsedPuzzle parse(String fileName) throws IOException, URISyntaxException;
}
