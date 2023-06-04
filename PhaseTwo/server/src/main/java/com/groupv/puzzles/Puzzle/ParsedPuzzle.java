package com.groupv.puzzles.Puzzle;

public class ParsedPuzzle<T> {

    private int size;
    private T[][] grid;

    /**
     * Constructs a new Puzzle with the given size and grid.
     *
     * @param size the size of the Puzzle
     * @param grid the grid of the Puzzle
     */
    public ParsedPuzzle(int size, T[][] grid) {
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
    public void setCell(int row, int col, T value) {
        grid[row][col] = value;
    }

    /**
     * Returns the value of the grid.
     *
     * @return the value of the grid
     */
    public T[][] getGrid() {
        return grid;
    }

    public String toString() {
        String grid = "";
        for (int i = 0; i < size; i++) {
            grid += "\n";
            for (int j = 0; j < size; j++) {
                grid += this.grid[i][j] + " ";
            }
        }
        return size + grid;
    }
}
