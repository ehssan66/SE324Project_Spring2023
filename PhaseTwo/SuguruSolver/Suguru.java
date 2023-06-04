import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Cell {
    int i;
    int j;
    char block;
    Set<Integer> marks;

    public Cell(int i, int j, char block) {
        this.i = i;
        this.j = j;
        this.block = block;
        this.marks = null;
    }

    public boolean solved() {
        return marks != null && marks.size() == 1;
    }

    @Override
    public String toString() {
        return "(" + i + ", " + j + ", " + block + ", " + marks + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}

class Suguru {
    Map<Character, List<Cell>> blocks;
    List<List<Cell>> grid;
    int width;
    int height;

    public Suguru() {
        this.blocks = new HashMap<>();
        this.grid = new ArrayList<>();
        this.width = 0;
        this.height = 0;
    }

    public void parseFile(String fname) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fname))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                List<Cell> cells = new ArrayList<>();
                for (int j = 0; j < line.length() / 2; j++) {
                    char val = line.charAt(2 * j);
                    char block = line.charAt(2 * j + 1);
                    Cell c = new Cell(i, j, block);
                    if (val != ' ') {
                        c.marks = new HashSet<>();
                        c.marks.add(Integer.parseInt(String.valueOf(val)));
                    }
                    blocks.computeIfAbsent(block, k -> new ArrayList<>()).add(c);
                    cells.add(c);
                }
                grid.add(cells);
                i++;
            }
            height = i;
            width = grid.get(0).size();
        }
        setMarks();
    }

    private void setMarks() {
        for (List<Cell> block : blocks.values()) {
            for (Cell cell : block) {
                if (cell.marks == null) {
                    cell.marks = new HashSet<>();
                    for (int val = 1; val <= block.size(); val++) {
                        cell.marks.add(val);
                    }
                }
            }
        }
    }

    public Cell getCell(int i, int j) {
        return grid.get(i).get(j);
    }

    public boolean isSolved() {
        for (List<Cell> line : grid) {
            for (Cell cell : line) {
                if (!cell.solved()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSolvedCount() {
        int count = 0;
        for (List<Cell> line : grid) {
            for (Cell cell : line) {
                if (cell.solved()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printProgress() {
        // implementation here
    }

    public void solveStep() {
        propagateBlock();
        propagateSolvedToNeighbors();
        pairsInBlock();
        blockSinglePos();
        propagateMarks();
    }

    private void propagateBlock() {
        for (List<Cell> block : blocks.values()) {
            Set<Integer> solved = new HashSet<>();
            for (Cell cell : block) {
                if (cell.solved()) {
                    solved.addAll(cell.marks);
                }
            }
            for (Cell cell : block) {
                if (!cell.solved()) {
                    cell.marks.removeAll(solved);
                }
            }
        }
    }

    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] direction : directions) {
            int i = cell.i + direction[0];
            int j = cell.j + direction[1];
            if (i >= 0 && i < height && j >= 0 && j < width) {
                neighbors.add(grid.get(i).get(j));
            }
        }
        return neighbors;
    }

    private void propagateSolvedToNeighbors() {
        for (List<Cell> line : grid) {
            for (Cell cell : line) {
                if (cell.solved()) {
                    List<Cell> neighbors = getNeighbors(cell);
                    for (Cell n : neighbors) {
                        n.marks.removeAll(cell.marks);
                    }
                }
            }
        }
    }

    private void pairsInBlock() {
        for (List<Cell> block : blocks.values()) {
            Map<Set<Integer>, Integer> countMap = new HashMap<>();
            for (Cell cell : block) {
                Set<Integer> marks = new HashSet<>(cell.marks);
                countMap.put(marks, countMap.getOrDefault(marks, 0) + 1);
            }
            for (Map.Entry<Set<Integer>, Integer> entry : countMap.entrySet()) {
                Set<Integer> marks = entry.getKey();
                int count = entry.getValue();
                if (count > 1 && marks.size() == count) {
                    for (Cell cell : block) {
                        if (!cell.solved() && !cell.marks.equals(marks)) {
                            cell.marks.removeAll(marks);
                        }
                    }
                }
            }
        }
    }

    private void blockSinglePos() {
        for (List<Cell> block : blocks.values()) {
            Map<Integer, List<Cell>> valCells = new HashMap<>();
            for (Cell cell : block) {
                for (int val : cell.marks) {
                    valCells.computeIfAbsent(val, k -> new ArrayList<>()).add(cell);
                }
            }
            for (Map.Entry<Integer, List<Cell>> entry : valCells.entrySet()) {
                int val = entry.getKey();
                List<Cell> cells = entry.getValue();
                if (cells.size() == 1 && !cells.get(0).solved()) {
                    cells.get(0).marks = new HashSet<>();
                    cells.get(0).marks.add(val);
                }
            }
        }
    }

    private void propagateMarks() {
        for (List<Cell> block : blocks.values()) {
            Set<Integer> unsolved = new HashSet<>();
            for (Cell c : block) {
                if (!c.solved()) {
                    unsolved.addAll(c.marks);
                }
            }
            for (int val : unsolved) {
                Set<Cell> cellsWithVal = new HashSet<>();
                for (Cell c : block) {
                    if (c.marks.contains(val)) {
                        cellsWithVal.add(c);
                    }
                }
                if (!cellsWithVal.isEmpty()) {
                    Set<Cell> common = new HashSet<>(getNeighbors(cellsWithVal.iterator().next()));
                    for (Cell cell : cellsWithVal) {
                        common.retainAll(getNeighbors(cell));
                    }
                    for (Cell cell : common) {
                        if (cell.marks.contains(val)) {
                            cell.marks.remove(val);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Cell> line : grid) {
            List<String> lineStr = new ArrayList<>();
            for (Cell c : line) {
                List<Integer> sortedMarks = new ArrayList<>(c.marks);
                Collections.sort(sortedMarks);
                StringBuilder marks = new StringBuilder();
                for (int mark : sortedMarks) {
                    marks.append(mark);
                }
                lineStr.add(c.block + ":" + marks.toString().replace("", " ").substring(1, 6));
            }
            sb.append(String.join(" ", lineStr)).append("\n");
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Suguru s = new Suguru();
        String fname = "6x6.txt";

        try {
            s.parseFile(fname);
            System.out.println(s);
            System.out.println(s.getSolvedCount());
            System.out.println();

            for (int i = 0; i < 10; i++) {
                s.solveStep();
                System.out.println(s);
                System.out.println(s.getSolvedCount());
                if (s.isSolved()) {
                    break;
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
