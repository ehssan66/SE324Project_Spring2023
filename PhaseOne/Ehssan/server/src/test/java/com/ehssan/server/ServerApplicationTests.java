package com.ehssan.server;

// import all needed libraries
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.ehssan.server.ServerApplication.PuzzleType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class ServerApplicationTests { // the server application tests

    private ServerApplication serverApplication = new ServerApplication(); // the server application

    /**
     * test the return unsolved puzzle picture 
     * partition the puzzle type
     * subdomains the puzzle type is unsolved puzzle
     * @throws IOException
     */
    @Test
    public void shouldReturnUnsolvedPuzzlePicture() throws IOException {
        ResponseEntity<byte[]> response = serverApplication.getPuzzlePicture(ServerApplication.PuzzleType.UnSolved_Puzzle);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        byte[] expectedBytes = Files.readAllBytes(Paths.get("src/main/resources/static/unsolved.png"));
        assertArrayEquals(expectedBytes, response.getBody());
    }

    /**
     * test the return solved puzzle picture 
     * partition the puzzle type
     * subdomains the puzzle type is solved puzzle
     * @throws IOException
     */
    @Test
    public void shouldReturnSolvedPuzzlePicture() throws IOException {
        ResponseEntity<byte[]> response = serverApplication.getPuzzlePicture(ServerApplication.PuzzleType.Solved_Puzzle);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        byte[] expectedBytes = Files.readAllBytes(Paths.get("src/main/resources/static/solved.png"));
        assertArrayEquals(expectedBytes, response.getBody());
    }

    /**
     * test the return invalid puzzle 
     * partition puzzle type
     * subdomains the puzzle type is null
     * @throws IOException
     */
    @Test
    public void shouldReturnNotFoundForInvalidPuzzleType() throws IOException {
        ResponseEntity<byte[]> response = serverApplication.getPuzzlePicture(PuzzleType.Null);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * test the load for unsolved puzzle picture by bytes method
     * partition the load picture method
     * subdomains the picture path is valid and it is unsolved puzzle
     * @throws IOException
     */
    @Test
    public void shouldLoadUnsolvedPuzzlePicture() throws IOException {
        byte[] imageBytes = serverApplication.loadPictureByBytes("static/unsolved.png");
        byte[] expectedBytes = Files.readAllBytes(Paths.get("src/main/resources/static/unsolved.png"));
        assertArrayEquals(expectedBytes, imageBytes);
    }
    
    /**
     * test the load for solved puzzle picture by bytes method
     * partition the load picture method
     * subdomains the picture path is valid and it is solved puzzle
     * @throws IOException
     */
    @Test
    public void shouldLoadSolvedPuzzlePicture() throws IOException {
        byte[] imageBytes = serverApplication.loadPictureByBytes("static/solved.png");
        byte[] expectedBytes = Files.readAllBytes(Paths.get("src/main/resources/static/solved.png"));
        assertArrayEquals(expectedBytes, imageBytes);
    }

    /**
     * test the load for invalid puzzle picture by bytes method
     * partition the load picture method
     * subdomains the picture path is invalid
     */
    @Test
    public void shouldThrowIOExceptionForInvalidPicturePath() {
        assertThrows(IOException.class, () -> serverApplication.loadPictureByBytes("invalid/path.png"));
    }
}
