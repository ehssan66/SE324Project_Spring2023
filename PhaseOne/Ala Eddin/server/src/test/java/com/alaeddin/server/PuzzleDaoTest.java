package com.alaeddin.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PuzzleDaoTest {
    /*
     * Testing strategy
     * 
     * covers these partitions: 
     *  partition on function: insertPuzzle, getNumberOfPuzzles, getPuzzleById
     * 
     * covers these subdomains: 
     *  insertPuzzle 
     *  getNumberOfPuzzles: empty DB, non-empty DB
     *  getPuzzleById: invalid id input, valid id input
     */

    private PuzzleDao puzzleDao;

    @Before
    public void setup() {
        puzzleDao = new PuzzleDao();
    }

    // covers insertPuzzle partition
    @Test
    public void testInsertPuzzle() {
        puzzleDao.insertPuzzle("url1", "solution1");
        Assert.assertNotEquals(0, puzzleDao.getNumberOfPuzzles());
    }

    // covers getNumberOfPuzzles partition
    @Test
    public void testGetNumberOfPuzzles() {
        // covers empty DB subdomain
        Assert.assertEquals(0, puzzleDao.getNumberOfPuzzles());

        // covers non-empty DB subdomain
        puzzleDao.insertPuzzle("url1", "solution1");
        puzzleDao.insertPuzzle("url2", "solution2");
        Assert.assertEquals(2, puzzleDao.getNumberOfPuzzles());
    }

    // covers getPuzzleById partition
    @Test
    public void testGetPuzzleById() {
        // covers invalid id input subdomain
        Assert.assertNull(puzzleDao.getPuzzleById(0));

        // covers valid id input subdomain
        puzzleDao.insertPuzzle("url1", "solution1");
        puzzleDao.insertPuzzle("url2", "solution2");
        Assert.assertNotNull(puzzleDao.getPuzzleById(0));
    }
}
