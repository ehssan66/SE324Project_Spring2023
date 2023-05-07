package com.ehssan;

//import all needed libraries
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.concurrent.CompletableFuture;

//create the app class
public class App extends Application { //extends application class

    private ImageView imageView; //create image view
    private Button solvedButton, unsolvedButton; //create buttons

    @Override //override the start method
    /**
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if there is an error loading the image
     */
    public void start(Stage primaryStage) throws Exception { 
        BorderPane root = new BorderPane(); //create border pane

        // Create the image view and set it in a stack pane
        imageView = new ImageView(); //create image view 
        StackPane stackPane = new StackPane(imageView); //create stack pane and add image view to it
        stackPane.setPadding(new Insets(10)); //set padding of stack pane

        // Create the "Solved Puzzle" button
        solvedButton = new Button("Get Solved Puzzle"); //create button 
        solvedButton.setFont(Font.font("Arial", 20)); //set font and size of button text 
        solvedButton.setTextFill(Color.WHITE); //set text color of button to white
        solvedButton.setStyle("-fx-background-color: #7C5BCE;"); //set background color of button to purple
        solvedButton.setOnAction(e -> displayPicture("http://localhost:8080/solved")); //set action of button to display solved puzzle picture

        // Create the "Unsolved Puzzle" button
        unsolvedButton = new Button("Get Unsolved Puzzle"); //create button
        unsolvedButton.setFont(Font.font("Arial", 20)); //set font and size of button text
        unsolvedButton.setTextFill(Color.WHITE); //set text color of button to white
        unsolvedButton.setStyle("-fx-background-color: #7C5BCE;"); //set background color of button to purple
        unsolvedButton.setOnAction(e -> displayPicture("http://localhost:8080/unsolved")); //set action of button to display unsolved puzzle picture

        // Add the buttons to a grid pane
        GridPane gridPane = new GridPane(); //create grid pane 
        gridPane.setAlignment(Pos.CENTER); //set alignment of grid pane to center 
        gridPane.setHgap(10); //set horizontal gap of grid pane to 10
        gridPane.setVgap(10); //set vertical gap of grid pane to 10
        gridPane.setPadding(new Insets(10)); //set padding of grid pane to 10
        gridPane.add(unsolvedButton, 0, 0); //add unsolved button to grid pane at position 0,0
        gridPane.add(solvedButton, 1, 0); //add solved button to grid pane at position 1,0

        // Add the stack pane and grid pane to a vertical box
        VBox vbox = new VBox(stackPane, gridPane); //create vertical box and add stack pane and grid pane to it
        vbox.setAlignment(Pos.CENTER); //set alignment of vertical box to center
        vbox.setSpacing(10); //set spacing of vertical box to 10
        root.setCenter(vbox); //set center of border pane to vertical box

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 700, 500); 
        primaryStage.setScene(scene); //set scene on the stage
        primaryStage.setTitle("Inki Puzzle"); //set title of stage to Inki Puzzle
        primaryStage.show(); //show stage
        

    }

    /**
     * Display the picture at the given URL in the image view
     * @param pictureUrl the URL of the picture to display in the image view
     * @throws Exception if there is an error loading the image
     */
    private void displayPicture(String pictureUrl) { //create method to display picture
        // Create a CompletableFuture to load the image in the background and update the UI when done
        CompletableFuture.supplyAsync(() -> { //create completable future
            try { //try to load image
                // Load the image from the URL
                return new Image(pictureUrl); //return image from url
            } catch (Exception e) { //catch exception
                System.err.println("Error loading image: " + e.getMessage()); // Print error message
                return null; //return null if error occurs in loading the picture
            }
        }).thenAcceptAsync(image -> { 
            // Update the UI with the loaded image
            if (image != null) { //if image is not null 
                imageView.setImage(image); //set image view to image
                imageView.getParent().getParent().getParent().requestLayout(); //request layout of image view
            }
        }, Platform::runLater); //run later on platform
    }
    
    /**
     * Launch the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

