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
import com.groupv.puzzles.PuzzleType.PuzzleTypeRepository;

// Testing Strategy: Integration Testing, Black Box Testing
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = PuzzlesApplication.class)
@AutoConfigureMockMvc
class PuzzleTypeTests {

	@Autowired
	private PuzzleTypeRepository puzzleTypeRepository;

	@Autowired
    private MockMvc mvc;

	// make sure the context loads
	// Partitions: puzzleTypeRepository is null, puzzleTypeRepository is not null
	// Partitions: puzzleTypeRepository is null
	@Test
	void contextLoads() throws Exception {
		assertThat(puzzleTypeRepository).isNotNull();
	}

	// Partitions: puzzleTypes exist, puzzleTypes do not exist
	// Subdomain: puzzleTypes exist
	@Test
	void return_puzzle_types_list_when_get_puzzle_types_and_status_200() throws Exception {
		mvc.perform(get("/api/puzzle-types")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.content").isArray())
		.andExpect(jsonPath("$.content[0].id", is(1)))
		.andExpect(jsonPath("$.content[0].name", is("suguru")))
		.andExpect(jsonPath("$.content[0].display_name", is("Suguru")));
	}

	// Partitions: puzzleTypeId is valid, puzzleTypeId is invalid
	// Subdomain: puzzleTypeId is valid
	@Test
	void return_puzzle_type_when_get_puzzle_type_by_name_and_status_200() throws Exception {
		mvc.perform(get("/api/puzzle-types/suguru")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("suguru"))
		);
	}

	// Partitions: puzzleTypeId is valid, puzzleTypeId is invalid
	// Subdomain: puzzleTypeId is invalid
	@Test
	void return_error_when_get_puzzle_type_by_name_that_doesnt_exist_and_status_404() throws Exception {
		mvc.perform(get("/api/puzzle-types/mansoor")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	// Partitions: puzzleType have puzzles, puzzleType does not have puzzles
	// Subdomain: puzzleType have puzzles
	@Test
	void return_puzzles_when_get_puzzle_type_puzzles_by_name_and_status_200() throws Exception {
		mvc.perform(get("/api/puzzle-types/suguru/puzzles")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.content").isArray())
			.andExpect(jsonPath("$.content[0].id", is(1))
		);
	}
}
