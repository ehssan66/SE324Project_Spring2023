package com.groupv.puzzles;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.groupv.puzzles.Puzzle.PuzzleRepository;

// Testing Strategy: Integration Testing, Black Box Testing
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = PuzzlesApplication.class)
@AutoConfigureMockMvc
class PuzzleTests {

	@Autowired
	private PuzzleRepository puzzleRepository;

	@Autowired
    private MockMvc mvc;

	// make sure the context loads
	// Partitions: puzzleRepository is null, puzzleRepository is not null
	// Partitions: puzzleRepository is null
	@Test
	void contextLoads() throws Exception {
        assertThat(puzzleRepository).isNotNull();
    }

	// Partitions: puzzles exist, puzzles do not exist
	// Subdomain: puzzles exist
    @Test
	void return_puzzle_list_when_get_puzzles_and_status_200() throws Exception {
		mvc.perform(get("/api/puzzles")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.content[0].id", is(1)))
		.andExpect(jsonPath("$.content[0].link", is("/public/suguru/puzzle-1.png")));
	}

	// Partitions: puzzle exists, puzzle does not exist
	// Subdomain: puzzle exists
    @Test
	void return_puzzle_list_when_get_puzzle_by_id_and_status_200() throws Exception {
		mvc.perform(get("/api/puzzles/1")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.link", is("/public/suguru/puzzle-1.png")));
	}

	// Partitions: puzzle have valid type, puzzle does not have valid type
	// Subdomain: puzzle have valid type
    @Test
	void return_puzzle_type_when_get_puzzle_puszle_type_and_status_200() throws Exception {
		mvc.perform(get("/api/puzzles/1/type")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("suguru")));
	}

	// Partitions: random puzzle returned, random puzzle not returned
	// Subdomain: random puzzle returned
    @Test
	void redirect_to_puzzle_when_get_random_puzzle_and_status_302_found() throws Exception {
		mvc.perform(get("/api/puzzle-types/suguru/puzzles/random")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isFound());
    }

	// Partitions: random puzzle exists, random puzzle does not exist
	// Subdomain: random puzzle does not exist
    @Test
	void return_404_notfound_when_get_random_puzzle_by_nonexisting_type() throws Exception {
		mvc.perform(get("/api/puzzle-types/mansoor/puzzles/random")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
    }

	// Partitions: random puzzle exists, random puzzle does not exist
	// Subdomain: random puzzle exists
    @Test
	void return_puzzle_when_redirected_from_get_puzzle_random() throws Exception {
		MvcResult result = mvc.perform(get("/api/puzzle-types/suguru/puzzles/random")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isFound())
        .andReturn();

        mvc.perform(get(result.getResponse().getRedirectedUrl())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.link", is("/public/suguru/puzzle-1.png")));
	}

	// Partitions: puzzle image exists, puzzle image does not exist
	// Subdomain: puzzle image exists
    @Test
    void return_png_image_when_request_puzzle_link_and_status_200() throws Exception {
        mvc.perform(get("/public/suguru/puzzle-1.png")
        .contentType(MediaType.IMAGE_PNG))
        .andExpect(status().isOk())
        .andExpect(content()
        .contentTypeCompatibleWith(MediaType.IMAGE_PNG));
    }
}