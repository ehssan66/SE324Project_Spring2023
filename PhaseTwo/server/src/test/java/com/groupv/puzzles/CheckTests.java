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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Testing Strategy: Integration Testing, Black Box Testing
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PuzzlesApplication.class)
@AutoConfigureMockMvc
class CheckTests {

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

    // Partitions: endpoint /api/puzzles/1/check correct solution, incorrect solution
    // Subdomain:  correct solution
    @Test
    void return_true_checked_suguru() throws Exception {
        String requestBody = "{\"content\":\"3a4b3b1b4c2c\\n5a1d5e2b3c1f\\n4a2e3e4e5c4g\\n1a5h1e2i1c2g\\n2a4h3i5i4i5g\\n3h1h2h1i3g1g\"}";

        mvc.perform(post("/api/puzzles/1/check")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true));
    }

    // Partitions: endpoint /api/puzzles/1/check correct solution, incorrect solution
    // Subdomain:  incorrect solution
    @Test
    void return_false_checked_suguru() throws Exception {
        String requestBody = "{\"content\":\"3a4\\n5a\\n4a2e\\n1a5h1\\n2a\\n3h\"}";
        mvc.perform(post("/api/puzzles/1/check")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(false));
    }

    // Partitions: endpoint /api/puzzles/2/check correct solution, incorrect solution
    // Subdomain:  correct solution
    @Test
    void return_true_checked_inkies() throws Exception {
        String requestBody = "{\"content\":\"2 3 1\\n1 2 3\\n3 1 2\"}";
        mvc.perform(post("/api/puzzles/2/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // Partitions: endpoint /api/puzzles/2/check correct solution, incorrect solution
    // Subdomain:  incorrect solution
    @Test
    void return_false_checked_inkies() throws Exception {
        String requestBody = "{\"content\":\"2 \\n1 3\\n3 \"}";
        mvc.perform(post("/api/puzzles/2/check")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(false));
    }

}

