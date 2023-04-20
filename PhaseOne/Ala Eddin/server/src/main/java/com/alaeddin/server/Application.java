package com.alaeddin.server;

import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


		// Initialize DB with some puzzles

		PuzzleDao puzzleDao = new PuzzleDao();

		// Get url to puzzle and solution images
		URL puzzle1Url = Application.class.getResource("/static/inky1.png");
		URL puzzle2Url = Application.class.getResource("/static/inky2.png");
		URL puzzle3Url = Application.class.getResource("/static/inky3.png");
		URL puzzle4Url = Application.class.getResource("/static/inky4.png");
		URL solution1Url = Application.class.getResource("/static/solution1.png");
		URL solution2Url = Application.class.getResource("/static/solution2.png");
		URL solution3Url = Application.class.getResource("/static/solution3.png");
		URL solution4Url = Application.class.getResource("/static/solution4.png");

		// Add puzzles to database
		puzzleDao.insertPuzzle(puzzle1Url.toString(), solution1Url.toString());
		puzzleDao.insertPuzzle(puzzle2Url.toString(), solution2Url.toString());
		puzzleDao.insertPuzzle(puzzle3Url.toString(), solution3Url.toString());
		puzzleDao.insertPuzzle(puzzle4Url.toString(), solution4Url.toString());
	}

}

