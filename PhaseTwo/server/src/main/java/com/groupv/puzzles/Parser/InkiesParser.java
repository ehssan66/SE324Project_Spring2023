package com.groupv.puzzles.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.groupv.puzzles.Puzzle.ParsedPuzzle;

public class InkiesParser implements PuzzleParser {

    /**
     * Parses the given file into a Puzzle object.
     *
     * @param fileName containing puzzle to parse
     * @return Puzzle object
     * @throws IOException if the file cannot be read
     * @throws URISyntaxException if the file path is invalid
     */
    @Override
    public ParsedPuzzle<String> parse(String fileName) throws IOException, URISyntaxException {

        URI uri = new URI(InkiesParser.class.getClassLoader().getResource("static/" + fileName).toString());
        String path = uri.getPath();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int size = Integer.parseInt(line);
        String[][] grid = new String[size][size];

        ParsedPuzzle<String> puzzle = new ParsedPuzzle<String>(size, grid);

        for (int i = 0; i < size; i++) {
            line = reader.readLine();
            String[] tokens = line.split(" ");
            for (int j = 0; j < size; j++) {
                if (tokens[j].equals("X")) {
                    puzzle.setCell(i, j, "X");
                } else {
                    String value = tokens[j];
                    puzzle.setCell(i, j, value);
                }
            }
        }

        reader.close();
        return puzzle;
    }
}
