package com.ehssan.server;

// import all needed libraries
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController 
public class ServerController{

    private final ServerApplication serverApplication; // the server application

    /**
     * constructor
     * @param serverApplication the server application
     */
    public ServerController(ServerApplication serverApplication) {
        this.serverApplication = serverApplication;
    }

    /**
     * get the unsolved puzzle picture 
     * @return the unsolved puzzle picture 
     * @throws IOException if the picture path is not found
     */
    @GetMapping("/unsolved") // the get mapping for unsolved puzzle picture
    public ResponseEntity<byte[]> getUnsolvedPuzzlePicture() throws IOException {
        return serverApplication.getPuzzlePicture(ServerApplication.PuzzleType.UnSolved_Puzzle); // return the unsolved puzzle picture
    }
    /**
     * get the solved puzzle picture
     * @return the solved puzzle picture 
     * @throws IOException if the picture path is not found
     */
    @GetMapping("/solved") // the get mapping for solved puzzle picture
    public ResponseEntity<byte[]> getSolvedPuzzlePicture() throws IOException {
        return serverApplication.getPuzzlePicture(ServerApplication.PuzzleType.Solved_Puzzle); // return the solved puzzle picture
    }
    }       

