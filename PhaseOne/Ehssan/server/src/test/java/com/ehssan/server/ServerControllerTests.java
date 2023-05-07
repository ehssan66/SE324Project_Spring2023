package com.ehssan.server;

// import all needed libraries
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

public class ServerControllerTests { // the server controller tests

    private ServerApplication serverApplication = Mockito.mock(ServerApplication.class); // the server application
    private ServerController serverController = new ServerController(serverApplication); // the server controller

    /**
     * test the return unsolved puzzle picture
     * partition the puzzle type
     * subdomains the puzzle type is unsolved puzzle
     * @throws IOException
     */
    @Test
    public void shouldReturnUnsolvedPuzzlePicture() throws IOException {
        byte[] expectedBytes = new byte[]{1, 2, 3, 4};
        Mockito.when(serverApplication.getPuzzlePicture(eq(ServerApplication.PuzzleType.UnSolved_Puzzle)))
                .thenReturn(ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(expectedBytes));

        ResponseEntity<byte[]> response = serverController.getUnsolvedPuzzlePicture();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        assertEquals(expectedBytes, response.getBody());
    }

    /**
     * test the return solved puzzle picture
     * partition the puzzle type
     * subdomains the puzzle type is solved puzzle
     * @throws IOException
     */
    @Test
    public void shouldReturnSolvedPuzzlePicture() throws IOException {
        byte[] expectedBytes = new byte[]{5, 6, 7, 8};
        Mockito.when(serverApplication.getPuzzlePicture(eq(ServerApplication.PuzzleType.Solved_Puzzle)))
                .thenReturn(ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(expectedBytes));

        ResponseEntity<byte[]> response = serverController.getSolvedPuzzlePicture();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        assertEquals(expectedBytes, response.getBody());
    }
    
}
