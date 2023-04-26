package com.ehssan.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        ProjectApplication.main(new String[] {});
    }

    // covers insertPuzzle
    @Test
    public void testInsertPuzzle() {
        PuzzleDao puzzleDao = new PuzzleDao();
        URL puzzleUrl = ProjectApplication.class.getResource("/static/unsolved1.png");
        URL solutionUrl = ProjectApplication.class.getResource("/static/solved1.png");
        puzzleDao.insertPuzzle(puzzleUrl.toString(), solutionUrl.toString());

        assertNotNull(puzzleDao.getPuzzleById(0));
        assertEquals(puzzleUrl.toString(), puzzleDao.getPuzzleById(0).getUrl());
        assertEquals(solutionUrl.toString(), puzzleDao.getPuzzleById(0).getSolution());
    }
}

