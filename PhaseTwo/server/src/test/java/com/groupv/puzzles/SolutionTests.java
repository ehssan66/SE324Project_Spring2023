package com.groupv.puzzles;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.groupv.puzzles.Solution.SolutionRepository;

// Testing Strategy: Integration Testing, Black Box Testing
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = PuzzlesApplication.class)
@AutoConfigureMockMvc
class SolutionTests {

	@Autowired
	private SolutionRepository solutionRepository;

	@Autowired
    private MockMvc mvc;

	// make sure the context loads
	// Partitions: solutionRepository is null, solutionRepository is not null
	// Subdomain: solutionRepository is null
	@Test
	void contextLoads() throws Exception {
        assertThat(solutionRepository).isNotNull();
    }

	// Partitions: puzzleId is valid, puzzleId is invalid
	// Subdomain: puzzleId is valid
    @Test
	void return_solutions_list_when_get_puzzle_by_id_and_status_200() throws Exception {
		mvc.perform(get("/api/puzzles/1/solutions")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.content[0].id", is(1)))
		.andExpect(jsonPath("$.content[0].link", is("/public/suguru/solution-1.png")));
	}


	// Partitions: puzzleId is valid, puzzleId is invalid
	// Subdomain: puzzleId is invalid
    @Test
	void return_not_found_when_get_puzzle_by_id_and_status_404() throws Exception {
		mvc.perform(get("/api/puzzles/0/solutions")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	// Partitions: solution image is valid, solution image is invalid
	// Subdomain: solution image is valid
    @Test
    void return_png_image_when_request_puzzle_solution_link_and_status_200() throws Exception {
        mvc.perform(get("/public/suguru/solution-1.png")
        .contentType(MediaType.IMAGE_PNG))
        .andExpect(status().isOk())
        .andExpect(content()
        .contentTypeCompatibleWith(MediaType.IMAGE_PNG));
    }
}