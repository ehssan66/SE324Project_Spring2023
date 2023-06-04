package com.groupv.puzzles.Puzzle;

public class PuzzleDao {

    private String content;

    public PuzzleDao() {}

    /**
     * Constructs a new PuzzleDoa with given content
     *
     * @param content of the Puzzle
     */
    public PuzzleDao(String content) {
        this.content = content;
    }
    /**
     * Returns puzzle content
     *
     * @return string content
     */
    public String getContent() {
        return content;
    }
}
