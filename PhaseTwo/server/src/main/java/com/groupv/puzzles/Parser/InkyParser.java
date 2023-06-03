package com.groupv.puzzles.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.groupv.puzzles.Puzzle.Puzzle;

public class InkyParser implements PuzzleParser {

    /**
     * Parses the given file into a Puzzle object.
     *
     * @param path the path to the file to parse
     * @return the Puzzle object
     * @throws IOException if the file cannot be read
     */
    @Override
    public Puzzle parse(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int size = Integer.parseInt(line);
        int[][] grid = new int[size][size];

        Puzzle puzzle = new Puzzle(size, grid);

        int value;

        for (int i = 0; i < size; i++) {
            line = reader.readLine();
            String[] tokens = line.split(" ");
            for (int j = 0; j < size; j++) {
                if (tokens[j].equals("X")) {
                    continue;
                } else {
                    value = Integer.parseInt(tokens[j]);
                    puzzle.setCell(i, j, value);
                }
            }
        }

        reader.close();
        return puzzle;
    }
}
