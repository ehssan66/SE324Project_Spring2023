package com.groupv.puzzles.Solver;

public class SolutionDto {
    String content;
    /**
     * Constructs SolutionDto with given content
     *
     * @param content of the Solution
     */
    public SolutionDto(String content) {
        this.content = content;
    }
    /**
     * Returns solution content
     *
     * @return string content
     */
    public String getContent() {
        return content;
    }
}
