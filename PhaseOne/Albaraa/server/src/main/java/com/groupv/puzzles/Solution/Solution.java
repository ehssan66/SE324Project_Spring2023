package com.groupv.puzzles.Solution;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;
import com.groupv.puzzles.Puzzle.Puzzle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a Solution.
 */
@Entity
@Data
@NoArgsConstructor
public class Solution {
    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinColumn(name="puzzle_id", nullable=false)
    @RestResource(path = "puzzles")
    private List<Puzzle> puzzles;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false, updatable=false)
    @CreationTimestamp
    private Date created_at;

    /**
     * Creates a new Solution object with the given list of Puzzles and link.
     * 
     * @param puzzles the list of Puzzles solved by this Solution
     * @param link the link to the Solution's details
     */
    public Solution(List<Puzzle> puzzles, String link) {
        this.puzzles = puzzles;
        this.link = link;
    }
}
