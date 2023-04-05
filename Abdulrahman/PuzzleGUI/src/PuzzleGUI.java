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
    private JPanel panel;
    private JLabel imageLabel;
    private JButton solvedButton, unsolvedButton;

    public PuzzleGUI() {
        super("Puzzle GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(236, 240, 241));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        Font buttonFont = new Font("Arial", Font.BOLD, 16);

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

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 5));

        add(panel, BorderLayout.EAST);
        add(imageLabel, BorderLayout.CENTER);

        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solvedButton) {
            displayImage("http://localhost:8080/Solved-Puzzle");
        } else if (e.getSource() == unsolvedButton) {
            displayImage("http://localhost:8080/Unsolved-Puzzle");
        }
    }

    private void displayImage(String imageURL) {
        try {
            URL url = new URL(imageURL);
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(url));
            imageLabel.setIcon(imageIcon);
            pack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PuzzleGUI();
    }
}
