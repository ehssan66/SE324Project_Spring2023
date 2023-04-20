package com.alaeddin.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Application extends JFrame {

    /**
     * Fetcher with url of the server API.
     */
    Fetcher fetcher = new Fetcher("http://localhost:8080/puzzle/random");

    /**
     * Random Puzzle fetched from server.
     */
    private Puzzle puzzle = fetcher.getRandomPuzzle();

    /**
     * Label to view an image.
     */
    JLabel imageLabel;

    /**
     * Button to request a new random puzzle.
     */
    JButton getPuzzleButton;

    /**
     * Button to request the current puzzle's solution
     */
    JButton showSolutionButton;

    /**
     * Constructor (Runnable) that builds the GUI
     */
    public Application() {
        super("Image Loader");

        imageLabel = new JLabel();
        getPuzzleButton = new JButton("Get a random puzzle");
        showSolutionButton = new JButton("Show puzzle's solution");

        getPuzzleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                puzzle = fetcher.getRandomPuzzle();
                try {
                    URL puzzleUrl = new URL(puzzle.getUrl());
                    ImageIcon puzzleIcon = new ImageIcon(puzzleUrl);
                    Image puzzleIconImage = puzzleIcon.getImage();
                    ImageIcon resizedPuzzleIcon = new ImageIcon(
                            puzzleIconImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH));
                    imageLabel.setIcon(resizedPuzzleIcon);
                    showSolutionButton.setEnabled(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Application.this,
                            "Failed to load puzzle: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showSolutionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    URL solutionUrl = new URL(puzzle.getSolution());
                    ImageIcon solutionIcon = new ImageIcon(solutionUrl);
                    Image solutionIconImage = solutionIcon.getImage();
                    ImageIcon resizedSolutionIcon = new ImageIcon(
                            solutionIconImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH));
                    imageLabel.setIcon(resizedSolutionIcon);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Application.this,
                            "Failed to load solution: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showSolutionButton.setEnabled(false);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(getPuzzleButton);
        buttonPanel.add(showSolutionButton);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Application();
            }
        });
    }
}
