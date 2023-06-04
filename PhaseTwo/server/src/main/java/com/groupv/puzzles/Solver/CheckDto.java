package com.groupv.puzzles.Solver;

public class CheckDto {
    Boolean success;

    public CheckDto() {}
    /**
     * Constructs a new PuzzleDto
     *
     * @param success boolean to be set
     */
    public CheckDto(Boolean success) {
        this.success = success;
    }

    /**
     * Returns success
     *
     * @return success boolean
     */
    public Boolean getSuccess() {
        return success;
    }
}
