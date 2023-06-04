package com.groupv.puzzles.Puzzle;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.groupv.puzzles.Solver.Solver;
import com.groupv.puzzles.Solver.SolverFactory;

/**
 * Entity class representing a Puzzle entity.
 */
@Entity
@Data
@NoArgsConstructor

public class Puzzle {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "puzzle_type_id", nullable = false)
    @RestResource(path = "type")
    private PuzzleType type;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    /**
     * Constructs a new Puzzle with the given PuzzleType and content.
     *
     * @param type the PuzzleType of the Puzzle
     * @param content of the Puzzle
     */
    public Puzzle(PuzzleType type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * Returns a Solver for this Puzzle.
     *
     * @return Solver for the Puzzle
     */
    public Solver solver() {
        return SolverFactory.create(this);
    }

}
