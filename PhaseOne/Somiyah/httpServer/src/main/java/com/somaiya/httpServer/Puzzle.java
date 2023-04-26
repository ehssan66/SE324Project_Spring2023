package com.somaiya.httpServer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Puzzle {


    /**
     * Defining the puzzle name
     */
    private int puzzleNumber;
    /**
     * Defining the puzzle name
     */
    private String puzzleName;
    /**
     * Defining the empty puzzle path
     */
    private String puzzlePath;
    /**
     * Defining the solved puzzle path
     */
    private String solutionPath;

    /**
     * Construct a Puzzle object
     * @Param puzzleNumber to identify object index
     * @param puzzleName to identify object name
     * @param puzzlePath to identify the path of empty puzzle image
     * @param solutionPath to identify the path of solved puzzle image
     */
    @JsonCreator
    public Puzzle(@JsonProperty("puzzleNumber") int puzzleNumber, @JsonProperty("puzzleName") String puzzleName,  @JsonProperty("puzzlePath") String puzzlePath,@JsonProperty("solutionPath") String solutionPath) {
        if(puzzleNumber <= 0)
            throw new IllegalArgumentException("IllegalArgumentException");
        if(puzzleName == null || puzzlePath == null || solutionPath == null)
            throw new NullPointerException("NullPointerException");
        this.puzzleNumber = puzzleNumber;
        this. puzzleName = puzzleName;
        this.puzzlePath = puzzlePath;
        this.solutionPath = solutionPath;
    }
    /**
     * Get the number of a puzzle object
     * @return number of a Puzzle object
     */
    public int getPuzzleNumber() {
        return puzzleNumber;
    }
    /**
     * Set the number of a puzzle object
     * @param puzzleNumber to define the number of Puzzle object
     */

    public void setPuzzleNumber(int puzzleNumber) {
        this.puzzleNumber = puzzleNumber;
    }
    /**
     * Get the name of a puzzle object
     * @return name of a Puzzle object
     */
    public String getPuzzleName() {
        return puzzleName;
    }
    /**
     * Set the name of a puzzle object
     * @param puzzleName to define the name of Puzzle object
     */
    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }
    /**
     * Get the empty image path of a puzzle object
     * @return path for the empty image of a Puzzle object
     */
    public String getPuzzlePath() {
        return puzzlePath;
    }
    /**
     * Set the name of a puzzle object
     * @param puzzlePath to define the path of puzzle image
     */
    public void setPuzzlePath(String puzzlePath) {
        this.puzzlePath = puzzlePath;
    }
    /**
     * Get the solution image path of a puzzle object
     * @return path for the solved image of a Puzzle object
     */
    public String getSolutionPath() {
        return solutionPath;
    }
    /**
     * Set the name of a puzzle object
     * @param solutionPath to define the path of solution image
     */
    public void setSolutionPath(String solutionPath) {
        this.solutionPath = solutionPath;
    }
}
