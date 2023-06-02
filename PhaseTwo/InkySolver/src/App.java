import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
       try{
        File file = new File("puzzle.txt");
        Scanner scanner = new Scanner(file);

        int size = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            size++;
        }

        scanner.close();
        scanner = new Scanner(file);

        int[][] clues = new int[size][2];
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (Character.isDigit(c)) {
                    clues[row][col] = Character.getNumericValue(c);
                } else {
                    clues[row][col] = 0;
                }
            }
            row++;
        }

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
       }} 
       
       catch (FileNotFoundException e){
    System.out.println("File not found.");
        }
    }
}
