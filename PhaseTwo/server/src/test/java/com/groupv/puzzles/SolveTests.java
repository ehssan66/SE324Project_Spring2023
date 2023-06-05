package com.groupv.puzzles;

import com.groupv.puzzles.Puzzle.PuzzleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Testing Strategy: Integration Testing, Black Box Testing
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PuzzlesApplication.class)
@AutoConfigureMockMvc
class SolveTests {

    @Autowired
    private PuzzleRepository puzzleRepository;

    @Autowired
    private MockMvc mvc;

    // make sure the context loads
    // Partitions: puzzleRepository is null, puzzleRepository is not null
    // Subdomain: puzzleRepository is not null
    @Test
    void contextLoads() throws Exception {
        assertThat(puzzleRepository).isNotNull();
    }

    // Partitions: valid endpoint /api/puzzles/1/solve
    // Subdomain: retrieve solution
    @Test
    void return_solved_suguru_by_id_and_status_200() throws Exception {
        mvc.perform(get("/api/puzzles/1/solve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("3a4b3b1b4c2c\n5a1d5e2b3c1f\n4a2e3e4e5c4g\n1a5h1e2i1c2g\n2a4h3i5i4i5g\n3h1h2h1i3g1g")));
    }

    // Partitions: valid endpoint /api/puzzles/2/solve
    // Subdomain: retrieve solution
    @Test
    void return_solved_inkies_by_id_and_status_200() throws Exception {
        mvc.perform(get("/api/puzzles/2/solve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("2 3 1\n1 2 3\n3 1 2")));
    }

}
/*{"content": "3a b b b c c\\na d e2b3c f\\na e e e c g\\na h e i c g\\na h i i i g\\nh h h i3g g"}*/
