package com.somaiya.httpServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Random;
@RequestMapping("api")
@RestController
public class PuzzleController {

    /**
     * Create puzzleRepo
     */
    PuzzleRepo puzzleRepo = new PuzzleRepo();
    /**
     * Define Puzzle Images Path
     */
    private URL PuzzlePath1 = httpServerApplication.class.getResource("/empty/puzzle1.png");
    private URL PuzzlePath2 = httpServerApplication.class.getResource("/empty/puzzle2.png");
    private URL PuzzlePath3 = httpServerApplication.class.getResource("/empty/puzzle3.png");
    private URL PuzzlePath4 = httpServerApplication.class.getResource("/empty/puzzle4.png");
    private URL PuzzlePath5 = httpServerApplication.class.getResource("/empty/puzzle5.png");
    private URL PuzzlePath6 = httpServerApplication.class.getResource("/empty/puzzle6.png");
    private URL PuzzlePath7 = httpServerApplication.class.getResource("/empty/puzzle7.png");

    private URL PuzzleSol1 = httpServerApplication.class.getResource("/solved/puzzle1.png");
    private URL PuzzleSol2 = httpServerApplication.class.getResource("/solved/puzzle2.png");
    private URL PuzzleSol3 = httpServerApplication.class.getResource("/solved/puzzle3.png");
    private URL PuzzleSol4 = httpServerApplication.class.getResource("/solved/puzzle4.png");
    private URL PuzzleSol5 = httpServerApplication.class.getResource("/solved/puzzle5.png");
    private URL PuzzleSol6 = httpServerApplication.class.getResource("/solved/puzzle6.png");
    private URL PuzzleSol7 = httpServerApplication.class.getResource("/solved/puzzle7.png");


    /**
     * Create Puzzle Objects with Images Path
     */
    private Puzzle P1 = new Puzzle(1,"Suguru#1",PuzzlePath1.toString(), PuzzleSol1.toString());
    private Puzzle P2 = new Puzzle(2,"Suguru#2",PuzzlePath2.toString(),PuzzleSol2.toString());
    private Puzzle P3 = new Puzzle(3,"Suguru#3",PuzzlePath3.toString(),PuzzleSol3.toString());
    private Puzzle P4 = new Puzzle(4,"Suguru#4",PuzzlePath4.toString(),PuzzleSol4.toString());
    private Puzzle P5 = new Puzzle(5,"Suguru#5",PuzzlePath5.toString(),PuzzleSol5.toString());
    private Puzzle P6 = new Puzzle(6,"Suguru#6",PuzzlePath6.toString(),PuzzleSol6.toString());
    private Puzzle P7 = new Puzzle(7,"Suguru#7",PuzzlePath7.toString(),PuzzleSol7.toString());

    /**
     * Set Puzzles into the repository
     * @return new puzzle object from the list
     */
    public void setPuzzles() {
        puzzleRepo.setPuzzleRepository(P1);
        puzzleRepo.setPuzzleRepository(P2);
        puzzleRepo.setPuzzleRepository(P3);
        puzzleRepo.setPuzzleRepository(P4);
        puzzleRepo.setPuzzleRepository(P5);
        puzzleRepo.setPuzzleRepository(P6);
        puzzleRepo.setPuzzleRepository(P7);
    }

    /**
     * Choose Puzzle from repository
     * @return new puzzle object from the list
     */
    public Puzzle getEmptyPuzzle() {
        setPuzzles();
        int New = new Random().nextInt(puzzleRepo.getSize()+1);
        while (New == 0){New = new Random().nextInt(puzzleRepo.getSize()+1);}
        int current = 1;
        for(int i = 1; i<=New; i++){
            current = i;
        }
        return puzzleRepo.getByPuzzleNo(current);
    }
    /**
     * Response with a new puzzle object
     * @return new empty puzzle object
     */
    @GetMapping(path = {"newPuzzle"})
    public Puzzle getNewPuzzle(){

        return getEmptyPuzzle();
    }

}
