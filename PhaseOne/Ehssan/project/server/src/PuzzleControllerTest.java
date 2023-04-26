package com.ehssan.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(serverController.class)
public class PuzzleControllerTest {
    /*
     * Testing strategy
     * 
     * covers these partitions: 
     *  partition on function: getRandomPuzzle returns a Puzzle object
     */

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private serverController puzzleService;

    // covers getRandomPuzzle partition
    @Test
    public void testGetRandomPuzzle() throws Exception {
        // Setup mock behavior
        puzzle mockPuzzle = new puzzle(0, "mockUrl", "mockSolution");
        Mockito.when(puzzleService.getRandomPuzzle()).thenReturn(mockPuzzle);

        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/puzzle/random"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(mockPuzzle.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.url").value(mockPuzzle.getUrl()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.solution")
                        .value(mockPuzzle.getSolution()));

        // Verify mock behavior
        Mockito.verify(puzzleService).getRandomPuzzle();
        Mockito.verifyNoMoreInteractions(puzzleService);
    }

}
