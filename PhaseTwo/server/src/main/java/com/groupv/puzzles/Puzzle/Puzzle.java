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
    @JoinColumn(name = "puzzle_type_id", nullable = false)
    @RestResource(path = "type")
    private PuzzleType type;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "puzzles")
    private List<Solution> solutions;

    private int size;
    private int[][] grid;

    /**
     * Constructs a new Puzzle with the given size and grid.
     *
     * @param size the size of the Puzzle
     * @param grid the grid of the Puzzle
     */
    public Puzzle(int size, int[][] grid) {
        this.size = size;
        this.grid = grid;
    }

    /**
     * Returns the size of the Puzzle.
     * 
     * @return the size of the Puzzle
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the Puzzle.
     * 
     * @param size the size of the Puzzle
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the value of the cell at the given row and column.
     *
     * @param row the row of the cell
     * @param col the column of the cell
     */
    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }

    /**
     * Returns the value of the grid.
     *
     * @return the value of the grid
     */
    public int[][] getGrid() {
        return grid;
    }

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

    public String toString() {
        String grid = "";
        for (int i = 0; i < size; i++) {
            grid += "\n";
            for (int j = 0; j < size; j++) {
                grid += this.grid[i][j] + " ";
            }
        }
        return "Puzzle: \n" + "size: " + size + "\ngrid: " + grid;
    }
}
