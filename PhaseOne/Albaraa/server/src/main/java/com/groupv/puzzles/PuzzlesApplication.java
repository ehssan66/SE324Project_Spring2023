package com.groupv.puzzles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class represents the main application class for running the Puzzles Application.
 */
@SpringBootApplication
public class PuzzlesApplication {

	/**
	*
	*	The entry point for the application.
	*	@param args The command line arguments for the application.
	*	@return void
	*	@throws Exception If an error occurs while running the Spring Boot application.
	*/
	public static void main(String[] args) {
		SpringApplication.run(PuzzlesApplication.class, args);
	}

}
