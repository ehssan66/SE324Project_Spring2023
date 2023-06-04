package com.groupv.puzzles;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import com.groupv.puzzles.PuzzleType.PuzzleTypeRepository;
import com.groupv.puzzles.Parser.InkiesParser;
import com.groupv.puzzles.Parser.SuguruParser;
import com.groupv.puzzles.Puzzle.Puzzle;
import com.groupv.puzzles.Puzzle.PuzzleRepository;


@Configuration
public class DatabaseSeeder {
    private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

    /**
     * Configures and seeds the database with sample data.
     * 
     * @param puzzleTypeRepository - Repository for PuzzleType entity
     * @param puzzleRepository - Repository for Puzzle entity
     * @param solutionRepository - Repository for Solution entity
     * @return A CommandLineRunner instance to run the seed process
     * @throws Exception If there is an issue during the seeding process
     */
    @Bean
    CommandLineRunner seed(PuzzleTypeRepository puzzleTypeRepository, PuzzleRepository puzzleRepository) {
        // create sample data
        PuzzleType suguru = new PuzzleType("suguru", "Suguru");
        PuzzleType inkies = new PuzzleType("inkies", "Inkies");
        
        try {
			// file is in static/public
			SuguruParser suguruParser = new SuguruParser();
			InkiesParser Inkiesparser = new InkiesParser();
            Puzzle puzzle1 = new Puzzle(suguru, suguruParser.parse("suguru.txt").toString());
            Puzzle puzzle2 = new Puzzle(inkies, Inkiesparser.parse("inkies.txt").toString());

            return args -> {
                log.info("Preloading " + puzzleTypeRepository.save(suguru));
                log.info("Preloading " + puzzleTypeRepository.save(inkies));
                log.info("Preloading " + puzzleRepository.save(puzzle1));
                log.info("Preloading " + puzzleRepository.save(puzzle2));
            };
		} catch (Exception e) {
            log.info("[error]");
			e.printStackTrace();
		}

        // preload sample data into the database
        return args -> {};
    }
}
