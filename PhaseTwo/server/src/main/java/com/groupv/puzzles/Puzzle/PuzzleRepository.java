package com.groupv.puzzles.Puzzle;

import org.springframework.data.jpa.repository.JpaRepository;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import java.util.Optional;
import java.util.List;


/**
 * Repository interface for Puzzle entities.
 */
public interface PuzzleRepository extends JpaRepository<Puzzle, Long> {
    /**
     * Counts the number of Puzzles with the given PuzzleType.
     *
     * @param puzzleType the PuzzleType to count Puzzles for
     * @return the number of Puzzles with the given PuzzleType
     */
    long countByType(PuzzleType puzzleType);

    Optional<List<Puzzle>> findByType(PuzzleType puzzleType);
}
