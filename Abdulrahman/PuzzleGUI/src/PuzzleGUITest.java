import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

public class PuzzleGUITest {
    private PuzzleGUI gui;

    @Before
    public void setUp() throws Exception {
        gui = new PuzzleGUI();
    }

    @Test
    public void testButtonProperties() {
        // Partition: button properties
        // Subdomain: button text, background color, foreground color, font, border, focus painted
        JButton solvedButton = gui.getSolvedButton();
        JButton unsolvedButton = gui.getUnsolvedButton();

        assertEquals("Solved Puzzle", solvedButton.getText());
        assertEquals("Unsolved Puzzle", unsolvedButton.getText());

        assertEquals(new Color(41, 128, 185), solvedButton.getBackground());
        assertEquals(new Color(231, 76, 60), unsolvedButton.getBackground());

        assertEquals(Color.WHITE, solvedButton.getForeground());
        assertEquals(Color.WHITE, unsolvedButton.getForeground());

        assertEquals(new Font("Arial", Font.BOLD, 16), solvedButton.getFont());
        assertEquals(new Font("Arial", Font.BOLD, 16), unsolvedButton.getFont());

        assertEquals(BorderFactory.createLineBorder(new Color(52, 73, 94), 5),
                solvedButton.getBorder());
        assertEquals(BorderFactory.createLineBorder(new Color(52, 73, 94), 5),
                unsolvedButton.getBorder());

        assertFalse(solvedButton.getFocusPainted());
        assertFalse(unsolvedButton.getFocusPainted());
    }

    @Test
    public void testImageDisplay() throws IOException {
        // Partition: image display
        // Subdomain: image URL, image loading, image size, image display
        String solvedURL = "https://dummyimage.com/300x300/000/fff.png&text=Solved+Puzzle";
        String unsolvedURL = "https://dummyimage.com/300x300/000/fff.png&text=Unsolved+Puzzle";

        // Test solved puzzle image display
        gui.displayImage(solvedURL);

        BufferedImage image = gui.getImageLabel().getImage();
        assertEquals(300, image.getWidth());
        assertEquals(300, image.getHeight());

        // Test unsolved puzzle image display
        gui.displayImage(unsolvedURL);

        image = gui.getImageLabel().getImage();
        assertEquals(300, image.getWidth());
        assertEquals(300, image.getHeight());
    }
}
