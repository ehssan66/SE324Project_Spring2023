package com.alaeddin.client;

public class Puzzle {

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
     * @throws IllegalArgumentException if any of the following is true: when the id is less than 0,
     *         when the url is empty (null), or when the solution is empty (null).
     */
    public Puzzle(int id, String url, String solution) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid id: " + id);
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("Invalid url: " + url);
        }
        if (solution == null || solution.isEmpty()) {
            throw new IllegalArgumentException("Invalid solution: " + solution);
        }
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
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }
        Puzzle puzzle = (Puzzle) that;
        return id == puzzle.id && url.equals(puzzle.url) && solution.equals(puzzle.solution);
    }

    /**
     * Returns a hash code for this Puzzle object.
     * 
     * @return a hash code for this Puzzle object
     */
    @Override
    public int hashCode() {
        int result = id;
        int primeNumber = 31;
        result = primeNumber * result + url.hashCode();
        result = primeNumber * result + solution.hashCode();
        return result;
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
