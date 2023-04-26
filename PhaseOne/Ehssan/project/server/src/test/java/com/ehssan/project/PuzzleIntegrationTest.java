package com.ehssan.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PuzzleIntegrationTest {
    /*
     * Testing strategy
     * 
     * integrate multiple classes to test the getRandomPuzzle function
     */

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PuzzleDao puzzleDao;

    private puzzle puzzle;

    @BeforeEach
    void setUp() {
        puzzleDao.insertPuzzle("Test Puzzle", "Test Solution");
    }

    @Test
    void getRandomPuzzle_returnsRandomPuzzle() throws Exception {
        when(puzzleDao.getNumberOfPuzzles()).thenReturn(1);
        when(puzzleDao.getPuzzleById(anyInt())).thenReturn(puzzle);

        mockMvc.perform(get("/puzzle/random"))
                .andExpect(status().isOk());
    }
}
