package com.ehssan.project;

import com.fasterxml.jackson.annotation.JsonProperty;

public class puzzle {

    /**
     * id to uniquely identify each Puzzle object.
     */
    private final int id;

    /**
     * url to the image of the puzzle.
     */
    private final String url;

    /**
     * url to the solution image of the puzzle.
     */
    private final String solution;

    /**
     * Constructs a new Puzzle object.
     * 
     * @param id to uniquely identify each Puzzle object
     * @param url url to the image of the puzzle
     * @param solution url to the solution image of the puzzle
     * @return 
     * @throws IllegalArgumentException if any of the following is true: when the id is less than 0,
     *         when the url is empty (null), or when the solution is empty (null).
     */
    public puzzle(@JsonProperty("id") int id, 
            @JsonProperty("url") String url,
            @JsonProperty("solution") String solution) {
        // Validate the input parameters
        if (id < 0) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("Invalid url: " + url);
        }
        if (solution == null || solution.isEmpty()) {
            throw new IllegalArgumentException("Invalid solution: " + solution);
        }
        
        // Set the object's properties
        this.id = id;
        this.url = url;
        this.solution = solution;
    }

    /**
     * Returns true if the given object is equal to this Puzzle object.
     * 
     * @param that the object to compare with this Puzzle object
     * @return true if the given object is equal to this Puzzle object
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {  // Check if the two objects are the same object in memory
            return true;
        }
        if (that == null || getClass() != that.getClass()) {  // Check if the compared object is null or is not a Puzzle object
            return false;
        }
        puzzle puzzle = (puzzle) that;  // Cast the compared object to a Puzzle object
        return id == puzzle.id && url.equals(puzzle.url) && solution.equals(puzzle.solution);  // Compare the object's properties
    }

    /**
     * Returns a hash code for this Puzzle object.
     * 
     * @return a hash code for this Puzzle object
     */
    @Override
    public int hashCode() {
        int result = id;  // Start with the id value
        int primeNumber = 31;  // A prime number to multiply the result with
        result = primeNumber * result + url.hashCode();  // Add the hash code of the url property
        result = primeNumber * result + solution.hashCode();  // Add the hash code of the solution property
        return result;  // Return the result
    }

    /**
     * Returns id of Puzzle.
     * 
     * @return id of Puzzle
     */
    public int getId() {
        return id;
    }

    /**
     * Returns url of Puzzle.
     * 
     * @return url of Puzzle
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns solution of Puzzle.
     * 
     * @return solution of Puzzle
     */
    public String getSolution() {
        return solution;
    }

}

