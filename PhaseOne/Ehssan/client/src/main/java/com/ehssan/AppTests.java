package com.ehssan.client;

//import all needed libraries
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//create the app test class
class AppTest { 

    private App app; //create app instance

    @BeforeAll //before all tests
    /**
     * Initialize JavaFX runtime to enable testing of JavaFX UI elements
     * @throws Exception if there is an error initializing the JavaFX runtime
     */
    static void init() {
        // Initialize JavaFX runtime to enable testing of JavaFX UI elements
        new JFXPanel(); 
    }

    @BeforeEach //before each test
    /**
     * Create a new app instance before each test
     * @throws Exception if there is an error creating the app instance
     */
    void setUp() {
        app = new App();
    }

    /**
     * Test that the displayImage method updates the image view with the correct image
     * partition display picture method
     * @param imageURL the URL of the image to display
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if there is an error loading the image
    */
    @Test
    @DisplayName("Test that the displayImage method updates the image view with the correct image")
    void displayImageTest() {
        // Set up a mock image URL to test the method with
        String imageURL = "https://dummyimage.com/200x200/000/fff.png";

        // Call the displayImage method with the mock image URL
        app.displayImage(imageURL);

        // Retrieve the ImageView from the app instance
        ImageView imageView = app.imageView;

        // Retrieve the CompletableFuture from the ImageView's image property
        CompletableFuture<Image> futureImage = (CompletableFuture<Image>) imageView.getImage().progressProperty().get();

        // Retrieve the loaded image from the CompletableFuture
        Image loadedImage = futureImage.join();

        // Assert that the loaded image is not null
        assertNotNull(loadedImage);

        // Assert that the loaded image's URL matches the mock image URL
        assertEquals(imageURL, loadedImage.getUrl());
    }

    /**
     * Test that the app's UI elements are initialized correctly
     * part of the partition UI elements initialized
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if there is an error launching the app
     * @throws AssertionError if the UI elements are not initialized correctly
     * @throws NullPointerException if the UI elements are not initialized correctly
     */
    @Test
    @DisplayName("Test that the app's UI elements are initialized correctly")
    void uiElementsInitializedTest() {
        // Launch the app
        Stage stage = new Stage();
        app.start(stage);

        // Retrieve the app's UI elements
        ImageView imageView = app.imageView;
        Button solvedButton = app.solvedButton;
        Button unsolvedButton = app.unsolvedButton;

        // Assert that the UI elements are not null
        assertNotNull(imageView);
        assertNotNull(solvedButton);
        assertNotNull(unsolvedButton);

        // Assert that the button text and styles are set correctly
        assertEquals("Get Solved Puzzle", solvedButton.getText());
        assertEquals("Get Unsolved Puzzle", unsolvedButton.getText());
        assertEquals("-fx-background-color: #7C5BCE;", solvedButton.getStyle());
        assertEquals("-fx-background-color: #7C5BCE;", unsolvedButton.getStyle());

        // Assert that the button fonts and text colors are set correctly
        assertEquals("Arial", solvedButton.getFont().getFamily());
        assertEquals(20, solvedButton.getFont().getSize());
        assertEquals(Color.WHITE, solvedButton.getTextFill());
        assertEquals("Arial", unsolvedButton.getFont().getFamily());
        assertEquals(20, unsolvedButton.getFont().getSize());
        assertEquals(Color.WHITE, unsolvedButton.getTextFill());
    }

    /**
     * Test that the app's UI elements are initialized correctly
     * part of the partition UI elements initialized
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws Exception if there is an error launching the app
     * @throws AssertionError if the UI elements are not initialized correctly
     */
    @Test
    void testDisplayImageWithValidUrl() {
        String validUrl = "https://via.placeholder.com/150";
        assertDoesNotThrow(() -> app.displayImage(validUrl));
    }

    @Test
    void testDisplayImageWithInvalidUrl() {
        String invalidUrl = "https://invalid-url.com";
        assertDoesNotThrow(() -> app.displayImage(invalidUrl));
    }

    @Test
    void testStart() {
        assertDoesNotThrow(() -> app.start(new Stage()));
    }
}
