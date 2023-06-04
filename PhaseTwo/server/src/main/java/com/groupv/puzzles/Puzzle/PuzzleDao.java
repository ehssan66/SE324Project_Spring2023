package com.groupv.puzzles.Puzzle;

public class PuzzleDao {
    private String content;

    public PuzzleDao() {}
    
    public PuzzleDao(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
