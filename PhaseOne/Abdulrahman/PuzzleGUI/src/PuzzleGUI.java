import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PuzzleGUI extends JFrame implements ActionListener {
    // Declare class-level variables
    private JPanel panel;
    private JLabel imageLabel;
    private JButton solvedButton, unsolvedButton;

    public PuzzleGUI() {
        // Set the title and close operation of the JFrame
        super("Puzzle GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new JPanel with a GridBagLayout
        panel = new JPanel(new GridBagLayout());

        // Set the background color and border of the JPanel
        panel.setBackground(new Color(236, 240, 241));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a new GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        // Create a new Font object
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Create a new "Solved Puzzle" button
        solvedButton = new JButton("Solved Puzzle");
        solvedButton.setFont(buttonFont);
        solvedButton.setBackground(new Color(41, 128, 185));
        solvedButton.setForeground(Color.WHITE);
        solvedButton.addActionListener(this);
        solvedButton.setPreferredSize(new Dimension(150, 150));
        solvedButton.setBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 5));
        solvedButton.setFocusPainted(false);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(solvedButton, gbc);

        // Create a new "Unsolved Puzzle" button
        gbc.gridx++;
        unsolvedButton = new JButton("Unsolved Puzzle");
        unsolvedButton.setFont(buttonFont);
        unsolvedButton.setBackground(new Color(231, 76, 60));
        unsolvedButton.setForeground(Color.WHITE);
        unsolvedButton.addActionListener(this);
        unsolvedButton.setPreferredSize(new Dimension(150, 150));
        unsolvedButton.setBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 5));
        unsolvedButton.setFocusPainted(false);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(unsolvedButton, gbc);

        // Create a new JLabel for the puzzle image
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 5));

        // Add the JPanel and JLabel to the JFrame
        add(panel, BorderLayout.EAST);
        add(imageLabel, BorderLayout.CENTER);

        // Set the size, location, and visibility of the JFrame
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * @param e to check if solved or unsolved then calls the displayImag with URL
     */
    public void actionPerformed(ActionEvent e) {
        // If the solved button is clicked
        if (e.getSource() == solvedButton) {

            // Display the image of the solved puzzle using its URL
            displayImage("http://localhost:8080/Solved-Puzzle");

            // If the unsolved button is clicked
        } else if (e.getSource() == unsolvedButton) {

            // Display the image of the unsolved puzzle using its URL
            displayImage("http://localhost:8080/Unsolved-Puzzle");
        }
    }

    /**
     * @param imageURL eused to represent the URL of an image that needs to be displayed
     */
    // Method to display the image from a given URL
    private void displayImage(String imageURL) {
        try {

            // Create a URL object from the given URL
            URL url = new URL(imageURL);

             // Read the image from the URL and create an ImageIcon object
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(url));

            // Set the imageIcon as the icon for the imageLabel
            imageLabel.setIcon(imageIcon);

            // Resize the frame to fit the image
            pack();
        } catch (IOException e) {

            // Print the stack trace if there's an error reading the image
            e.printStackTrace();
        }
    }

    // Main method to create an instance of the PuzzleGUI class
    public static void main(String[] args) {

        // Create a new instance of the PuzzleGUI class
        new PuzzleGUI();
    }
}
