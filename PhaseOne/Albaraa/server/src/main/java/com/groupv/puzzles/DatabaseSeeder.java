package com.groupv.puzzles;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import com.groupv.puzzles.PuzzleType.PuzzleTypeRepository;
import com.groupv.puzzles.Solution.Solution;
import com.groupv.puzzles.Solution.SolutionRepository;
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
    CommandLineRunner seed(PuzzleTypeRepository puzzleTypeRepository, PuzzleRepository puzzleRepository, SolutionRepository solutionRepository) {
        // create sample data
        PuzzleType suguru = new PuzzleType("suguru", "Suguru");
        Puzzle puzzle = new Puzzle(suguru, "/public/suguru/puzzle-1.png");
        Solution solution = new Solution(List.of(puzzle), "/public/suguru/solution-1.png");

        // preload sample data into the database
        return args -> {
            log.info("Preloading " + puzzleTypeRepository.save(suguru));
            log.info("Preloading " + puzzleRepository.save(puzzle));
            log.info("Preloading " + solutionRepository.save(solution));
        };
    }
}
