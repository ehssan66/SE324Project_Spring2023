package com.ehssan.server;

// import all needed libraries
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.nio.file.Files;


@SpringBootApplication
public class ServerApplication {

	// enum for puzzle type
    public enum PuzzleType {
        UnSolved_Puzzle,
        Solved_Puzzle,
		Null
    }

	/**
	 * get the puzzle picture method
	 * @param type the puzzle type
	 * @return the picture path or HttpStatus.NOT_FOUND if the type is null
	 * @throws IOException if the picture path is not found
	 */
	 
	 public ResponseEntity<byte[]> getPuzzlePicture(PuzzleType type) throws IOException{
		String picturePath; // the picture path
		switch (type) { // switch on the puzzle type
			case UnSolved_Puzzle -> picturePath = "static/unsolved.png"; // set the picture path for unsolved puzzle
			case Solved_Puzzle -> picturePath = "static/solved.png"; // set the picture path for solved puzzle
			default -> { // if the type is null
				return ResponseEntity.notFound().build(); // return HttpStatus.NOT_FOUND
			}
		}
		byte[] imageBytes = loadPictureByBytes(picturePath); // load the picture bytes
    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes); // return the picture
}

	/**
	 * load the picture by bytes
	 * @param picturePath the picture path
	 * @return the picture in bytes array
	 * @throws IOException if the picture path is not found
	 */
	byte[] loadPictureByBytes(String picturePath) throws IOException {
		ClassPathResource classPathResource = new ClassPathResource(picturePath); // get the picture path
		return Files.readAllBytes(classPathResource.getFile().toPath()); // return the picture in bytes array
	}

	/**
	 * main method
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args); // run the server
	}

}
