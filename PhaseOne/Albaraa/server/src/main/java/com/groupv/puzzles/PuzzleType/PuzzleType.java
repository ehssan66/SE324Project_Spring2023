package com.groupv.puzzles.PuzzleType;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;
import com.groupv.puzzles.Puzzle.Puzzle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a PuzzleType.
 */
@Entity
@Data
@NoArgsConstructor
@RestResource(path = "puzzle-types")
public class PuzzleType {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String display_name;

    @Column(nullable = false, updatable=false)
    @CreationTimestamp
    private Date created_at;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<Puzzle> puzzles;

    /**
     * Constructor for creating a new PuzzleType with the given name and display name.
     *
     * @param name the name of the PuzzleType
     * @param display_name the display name of the PuzzleType
     */
    public PuzzleType(String name, String display_name) {
        this.name = name;
        this.display_name = display_name;
    }
}
