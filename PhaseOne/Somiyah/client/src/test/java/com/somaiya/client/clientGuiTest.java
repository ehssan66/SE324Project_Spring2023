package com.somaiya.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class clientGuiTest {

    private clientRequest mockedClientRequest;
    private Puzzle mockedPuzzle;
    private clientGui gui;
    private JButton newPuzzButton;
    private JButton solButton;
    private JLabel puzzleImg;

    //Create Instance to Test (SERVER MUST BE CONNECTED TO PASS!!!)
    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        mockedClientRequest = Mockito.mock(clientRequest.class);
        mockedPuzzle = Mockito.mock(Puzzle.class);
        gui = new clientGui();
        newPuzzButton = gui.NewPuzzButton;
        solButton = gui.SolButton;
        puzzleImg = gui.puzzleImg;

        when(mockedClientRequest.requestPuzzle()).thenReturn(mockedPuzzle);
        when(mockedPuzzle.getPuzzlePath()).thenReturn("https://example.com/image.png");
        when(mockedPuzzle.getSolutionPath()).thenReturn("https://example.com/solution.png");
    }

    //Test action when NewPuzzButton clicked image is loaded
    //Partition doClick, NotNull image, Correct Image dimensions after scale
    @Test
    void testNewPuzzButtonAction() throws IOException, InterruptedException {
        // Click the new puzzle button
        newPuzzButton.doClick();

        // Verify that the puzzleImg JLabel has a new icon set
        assertNotNull(puzzleImg.getIcon());
        assertEquals(660, ((ImageIcon) puzzleImg.getIcon()).getIconWidth());
        assertEquals(660, ((ImageIcon) puzzleImg.getIcon()).getIconHeight());

    }
    //Test action when SolButton clicked image is loaded
    //Partition NotNull image, Correct Image dimensions after scale
    @Test
    void testSolButtonAction() throws IOException, InterruptedException {
        // Click the new puzzle button
        newPuzzButton.doClick();

        // Click the solve button
        solButton.doClick();

        // Verify that the puzzleImg JLabel has a new icon set
        assertNotNull(puzzleImg.getIcon());
        assertEquals(660, ((ImageIcon) puzzleImg.getIcon()).getIconWidth());
        assertEquals(660, ((ImageIcon) puzzleImg.getIcon()).getIconHeight());
    }

}