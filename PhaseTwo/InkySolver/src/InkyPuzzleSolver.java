public class InkyPuzzleSolver {

    private int[][] grid;
    private int[][] clues;
    private int size;

    public InkyPuzzleSolver(int[][] clues) {
        this.clues = clues;
        this.size = clues.length;
        this.grid = new int[size][size];
    }

    /**
    * call solve() to start solving 
    * @return  Solved grid if found or null if not.
    */
    public int[][] solve() {
        if (solvePuzzle(0, 0)) {
            return grid;
        }
        return null; // No solution found
    }

    /**
     * recursive approach that fills the cells backwards in an effort to solve the puzzle. 
     * @param row to take the row index
     * @param col to take the column index
     * 
     * @return true for valid solved or false for invalid solved
     */
    private boolean solvePuzzle(int row, int col) {
        if (row == size) {
            return true; // Base case: Puzzle solved
        }

        int nextRow = (col == size - 1) ? row + 1 : row;
        int nextCol = (col + 1) % size;

        if (grid[row][col] != 0) {
            return solvePuzzle(nextRow, nextCol); // Skip already filled cells
        }

        for (int i = 0; i <= 1; i++) {
            grid[row][col] = i;
            if (isValid(row, col) && solvePuzzle(nextRow, nextCol)) {
                return true;
            }
        }

        grid[row][col] = 0; // Backtrack
        return false;
    }

    /**
     * Validates the current row, column, and loop to determine whether the puzzle's current state is valid. 
     * @param row to take the row index
     * @param col to take the column index
     * 
     * @return true for valid state or false for invalid state
     */
    private boolean isValid(int row, int col) {
        return isValidRow(row) && isValidColumn(col) && isValidLoop();
    }

    /**
     * Determines whether the amount of black cells in the specified row corresponds to the clue for that row. 
     * @param row to take the row index to check
     * 
     * @return true for valid row or false for invalid row
     */
    private boolean isValidRow(int row) {
        int blackCount = 0;
        int clue = clues[row][0];
        for (int i = 0; i < size; i++) {
            if (grid[row][i] == 1) {
                blackCount++;
            }
        }
        return clue == 0 || blackCount == clue;
    }

    /**
     * Determines whether the amount of black cells in the specified column corresponds to the clue for that column. 
     * @param col to take the column index to check
     * 
     * @return true for valid column or false for invalid column
     */
    private boolean isValidColumn(int col) {
        int blackCount = 0;
        int clue = clues[col][1];
        for (int i = 0; i < size; i++) {
            if (grid[i][col] == 1) {
                blackCount++;
            }
        }
        return clue == 0 || blackCount == clue;
    }

    /**
     * Determines whether the grid contains a valid loop of black cells.
     * 
     * @return true for valid loop or false for invalid loop.
     */
    private boolean isValidLoop() {
        boolean[][] visited = new boolean[size][size];
        int startX = -1;
        int startY = -1;

        // Find the starting cell of the loop
        outer:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                    break outer;
                }
            }
        }

        if (startX == -1 || startY == -1) {
            return false; // No black cells found
        }

        // Perform DFS to validate the loop
        return dfs(startX, startY, startX, startY, visited);
    }

    /**
     * A depth-first search (DFS) recursive method is used to verify the grid's loop of black cells. 
     * @param row to take the row index
     * @param col to take the column index
     * @param prevRow to take the previous cell's row index
     * @param prevCol to take the previous cell's column index
     * @param visited to know a 2D boolean array displaying the cells that were visited or not.
     * 
     * @return true for valid loop or false for invalid loop
     */
    private boolean dfs(int row, int col, int prevRow, int prevCol, boolean[][] visited) {
        visited[row][col] = true;

        int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (isValidCell(newRow, newCol) && (newRow != prevRow || newCol != prevCol)) {
                if (grid[newRow][newCol] == 1) {
                    if (visited[newRow][newCol]) {
                        return false; // Loop contains a branch or crossing
                    } else {
                        if (!dfs(newRow, newCol, row, col, visited)) {
                            return false; // DFS from neighboring cell returns false
                        }
                    }
                } else {
                    return false; // Loop contains white cell or gap
                }
            }
        }

        return true; // Loop is valid
    }

    /**
     * Determines whether the specified cell coordinates are inside the grid's boundaries. 
     * @param row to take the row index
     * @param col to take the column index
     * 
     * @return true for valid cell  or false for invalid cell 
     */
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public static void main(String[] args) {
        int[][] clues = {
            { 0, 0 },
            { 2, 0 },
            { 0, 2 },
            { 0, 0 }
        };

        InkyPuzzleSolver solver = new InkyPuzzleSolver(clues);
        int[][] solution = solver.solve();

        if (solution != null) {
            for (int i = 0; i < solution.length; i++) {
                for (int j = 0; j < solution[i].length; j++) {
                    System.out.print(solution[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}