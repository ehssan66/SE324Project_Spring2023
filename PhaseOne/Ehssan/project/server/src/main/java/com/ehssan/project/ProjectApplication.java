package com.ehssan.project;

import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ProjectApplication {

	public static void main(String[] args) {
		initializePuzzles();
	}

	public static void initializePuzzles() {
		// Get url to puzzle and solution images
		URL[] puzzleUrls = {
			getResourceUrl("/static/unsolved1.png"),
			getResourceUrl("/static/unsolved2.png")
		};
		URL[] solutionUrls = {
			getResourceUrl("/static/solved1.png"),
			getResourceUrl("/static/solved2.png")
		};

		// Initialize DB with some puzzles
		PuzzleDao puzzleDao = new PuzzleDao();
		for (int i = 0; i < puzzleUrls.length; i++) {
			puzzleDao.insertPuzzle(puzzleUrls[i].toString(), solutionUrls[i].toString());
		}
	}

	private static URL getResourceUrl(String path) {
		return ProjectApplication.class.getResource(path);
	}

}
