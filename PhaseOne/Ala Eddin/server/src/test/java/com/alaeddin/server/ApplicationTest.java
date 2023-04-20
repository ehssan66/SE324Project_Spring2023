package com.alaeddin.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    /*
     * Testing strategy
     * 
     * covers these partitions:
     *  partition on function: contextLoads, insertPuzzle
     */

    // covers contextLoads
    @Test
    public void contextLoads() {
        // Verify that the Spring application starts correctly
        Application.main(new String[] {});
    }

    // covers insertPuzzle
    @Test
    public void testInsertPuzzle() {
        PuzzleDao puzzleDao = new PuzzleDao();
        URL puzzleUrl = Application.class.getResource("/static/inky1.png");
        URL solutionUrl = Application.class.getResource("/static/solution1.png");
        puzzleDao.insertPuzzle(puzzleUrl.toString(), solutionUrl.toString());

        assertNotNull(puzzleDao.getPuzzleById(0));
        assertEquals(puzzleUrl.toString(), puzzleDao.getPuzzleById(0).getUrl());
        assertEquals(solutionUrl.toString(), puzzleDao.getPuzzleById(0).getSolution());
    }
}

