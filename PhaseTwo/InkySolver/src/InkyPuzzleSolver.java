public class InkyPuzzleSolver {

    private int[][] grid;
    private int[][] clues;
    private int size;

    public InkyPuzzleSolver(int[][] clues) {
        this.clues = clues;
        this.size = clues.length;
        this.grid = new int[size][size];
    }

    public int[][] solve() {
        if (solvePuzzle(0, 0)) {
            return grid;
        }
        return null; // No solution found
    }

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

    private boolean isValid(int row, int col) {
        return isValidRow(row) && isValidColumn(col) && isValidLoop();
    }

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