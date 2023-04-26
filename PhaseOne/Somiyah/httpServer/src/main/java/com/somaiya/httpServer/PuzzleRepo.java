package com.somaiya.httpServer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PuzzleRepo {

    /**
     * Defining a List to store the Puzzle objects on it
     */
    private List<Puzzle> PuzzleRepository = new ArrayList<>();
    /**
     * Instantiate Puzzle objects and set them inside the list whenever an instance is made
     */
    public PuzzleRepo(){

    }
    /**
     * Set the puzzle objects into the list
     * @param P to add a puzzle object into the puzzle list
     */
    public void setPuzzleRepository(Puzzle P) {
        PuzzleRepository.add(P);
    }
    /**
     * Get a Puzzle object by its number
     * @param PuzzleNo to identify the selected puzzle
     * @return Puzzle object from the Puzzle list
     */
    public Puzzle getByPuzzleNo(int PuzzleNo){
        if (PuzzleNo <= 0)
            throw new IndexOutOfBoundsException();
        if(PuzzleNo > PuzzleRepository.size())
            throw new IndexOutOfBoundsException();
        if (PuzzleRepository.size() == 0)
            return null;
        else return PuzzleRepository.get(PuzzleNo-1);
    }
    /**
     * Get the size of the puzzle list
     * @return number of puzzles inside the list
     */
    public int getSize(){
        return this.PuzzleRepository.size();
    }

    public boolean RepoContains(Puzzle puzzle) {
        return this.PuzzleRepository.contains(puzzle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleRepo that = (PuzzleRepo) o;
        return Objects.equals(PuzzleRepository, that.PuzzleRepository);
    }
}
