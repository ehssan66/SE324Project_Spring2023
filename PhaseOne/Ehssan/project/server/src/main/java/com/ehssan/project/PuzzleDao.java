package com.ehssan.project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("puzzleDao")
public class PuzzleDao {

    /**
     * Shared HashMap instance that stores all Puzzle objects and serves as a database.
     */
    private static Map<Integer, puzzle> DB = new HashMap<>();

    /**
     * Inserts a Puzzle object in DB.
     * 
     * @param puzzle Puzzle object that is to be inserted
     */
    public void insertPuzzle(String url, String solution) {
        int index = DB.size();
        DB.put(index, new puzzle(index, url, solution));
    }

    /**
     * Returns the number of Puzzle objects (elements) in DB.
     * 
     * @return size of DB
     */
    public int getNumberOfPuzzles() {
        return DB.size();
    }

    /**
     * Returns a Puzzle object based on the provided id, which is the same as the key of the
     * Puzzle object in DB.
     * 
     * @param id key of the Puzzle object to be returned
     * @return Puzzle object of key id in DB, and null when DB does not contain the key
     */
    public puzzle getPuzzleById(int id) {
        return DB.getOrDefault(id, null);
    }

}

