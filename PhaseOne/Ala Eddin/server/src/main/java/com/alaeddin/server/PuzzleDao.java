package com.alaeddin.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("puzzleDao")
public class PuzzleDao {

    /**
     * Shared ArrayList instance that stores all Puzzle objects and serves as a database.
     */
    private static List<Puzzle> DB = new ArrayList<>();

    /**
     * Inserts a Puzzle object in DB.
     * 
     * @param puzzle Puzzle object that is to be inserted
     */
    public void insertPuzzle(String url, String solution) {
        int index = DB.size();
        DB.add(new Puzzle(index, url, solution));
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
     * Returns a Puzzle object based on the provided id, which is the same as the index of the
     * Puzzle object in DB.
     * 
     * @param id index of the Puzzle object to be returned
     * @return Puzzle object of index id in DB, and null when DB is empty
     * @throws IndexOutOfBoundsException when id is out of bounds
     */
    public Puzzle getPuzzleById(int id) {
        if (DB.size() == 0)
            return null;
        if (DB.size() <= id)
            throw new IndexOutOfBoundsException();
        return DB.get(id);
    }

}
