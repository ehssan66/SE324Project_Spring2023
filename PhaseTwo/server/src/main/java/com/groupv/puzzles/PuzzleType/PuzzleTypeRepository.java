package com.groupv.puzzles.PuzzleType;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for PuzzleType entity.
 */
public interface PuzzleTypeRepository extends JpaRepository<PuzzleType, Long> {
    /**
     * Retrieves an Optional containing the PuzzleType with the given name,
     * or an empty Optional if no PuzzleType exists with that name.
     *
     * @param name the name of the PuzzleType to retrieve
     * @return an Optional containing the PuzzleType with the given name, if it exists
     */
    Optional<PuzzleType> findByName(String name);
}
