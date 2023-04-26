package com.groupv.puzzles.Puzzle;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;
import com.groupv.puzzles.PuzzleType.PuzzleType;
import com.groupv.puzzles.Solution.Solution;
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
    @JoinColumn(name="puzzle_type_id", nullable=false)
    @RestResource(path = "type")
    private PuzzleType type;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false, updatable=false)
    @CreationTimestamp
    private Date created_at;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "puzzles")
    private List<Solution> solutions;

    /**
     * Constructs a new Puzzle with the given PuzzleType and link.
     *
     * @param type the PuzzleType of the Puzzle
     * @param link the link to the Puzzle
     */
    public Puzzle(PuzzleType type, String link) {
        this.type = type;
        this.link = link;
    }
}
