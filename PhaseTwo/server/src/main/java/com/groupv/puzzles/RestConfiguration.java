package com.groupv.puzzles;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import com.groupv.puzzles.Puzzle.Puzzle;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import com.groupv.puzzles.PuzzleType.PuzzleTypeRepository;
import com.groupv.puzzles.Solution.Solution;

/**
 * Configures the repository REST configuration by setting default media type, base path, and entity lookups.
 *
 * @param config the repository REST configuration object to configure
 * @param cors the Cross-Origin Resource Sharing (CORS) registry object to configure
 * @return void
 */
@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration config, CorsRegistry cors) {
        // Set the default content type to application/json
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        
        // Disable HAL as default JSON media type
        config.useHalAsDefaultJsonMediaType(false);

        // Set the base path for the API endpoints to /api
        config.setBasePath("/api");

        // Expose the ids of the PuzzleType, Puzzle, and Solution entities
        config.exposeIdsFor(PuzzleType.class, Puzzle.class, Solution.class);

        // Configure entity lookups for the PuzzleTypeRepository using the name field
        // and the findByName method
        config.withEntityLookup().forRepository(PuzzleTypeRepository.class, PuzzleType::getName, PuzzleTypeRepository::findByName);
    }
}