package com.somaiya.client;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
public class clientGui extends JFrame{

    /**
     * JLabel to show the requested Image
     */
     JLabel puzzleImg;
    /**
     * Image to set the frame  & app Icon
     */
     Image icon;
    /**
     * Panel to manage the Layout
     */
     JPanel panel;
     JPanel buttonPanel;
    /**
     * Buttons to choose the puzzle
     */
     JButton NewPuzzButton;

     JButton SolButton;
    /**
     * clientRequest to connect to the server and request a puzzle
     */
     static clientRequest clientRequest  = new clientRequest();
    /**
     * Puzzle object to get the  requested puzzle
     */
     Puzzle requestedP = clientRequest.requestPuzzle();

    /**
     * Constructor to run the GUI
     */
    public clientGui() throws IOException, InterruptedException {

        /**
         * Set up the look and feel
         */
        FlatMacDarkLaf.setup();
        /**
         * Setting the Tiltle, Size, CloseOperation
         */
        this.setTitle("Suguru Puzzle");
        this.setSize(770, 770);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * Setting the Frame icon
         */
        icon = new ImageIcon(this.getClass().getResource("/suguru.png")).getImage();
        this.setIconImage(icon);
         buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        /**
         * Setting the button acton to get the parsed object's image
         */
        NewPuzzButton = new JButton("Get Puzzle");
        NewPuzzButton.addActionListener(e -> {
                try {
                    requestedP = clientRequest.requestPuzzle();
                    URL url = new URL(requestedP.getPuzzlePath());
                    Image image = ImageIO.read(url);
                    ImageIcon icon = new ImageIcon(image);
                    Image puzzleImage = icon.getImage();
                    ImageIcon newSizeImage = new ImageIcon(puzzleImage.getScaledInstance(660, 660,Image.SCALE_SMOOTH));
                    puzzleImg.setIcon(newSizeImage);
                    buttonPanel.add(SolButton);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Failed to get puzzle: " + ex.getMessage());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);

                }
        });
        buttonPanel.add(NewPuzzButton);

        /**
         * Setting the button action to get the parsed object's solution image
         */
        SolButton = new JButton("Solve");
        SolButton.addActionListener(e -> {
            if (requestedP.getSolutionPath() == null) {
                JOptionPane.showMessageDialog(this, "No puzzle image loaded.");
                return;
            }
                try {
                    URL url = new URL(requestedP.getSolutionPath());
                    Image image = ImageIO.read(url);
                    ImageIcon icon = new ImageIcon(image);
                    Image puzzleImage = icon.getImage();
                    ImageIcon newSizeImage = new ImageIcon(puzzleImage.getScaledInstance(660, 660,Image.SCALE_SMOOTH));
                    puzzleImg.setIcon(newSizeImage);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Failed to get solved puzzle: " + ex.getMessage());

                }
        });

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        /**
         * Adding the image to the panel and showing the frame
         */

        puzzleImg = new JLabel();
        panel.add(puzzleImg, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);

        add(panel);

        setVisible(true);
    }

    /**
     * Run the clientGUI
     * @Param String[] args
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        new clientGui();
    }
}
