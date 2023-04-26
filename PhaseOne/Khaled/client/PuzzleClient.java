import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class PuzzleClient extends JFrame {

    private JButton btnPuzzle1;
    private JButton btnPuzzle2;
    private JLabel image;

    public PuzzleClient() {
        setTitle("Puzzle Client");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        btnPuzzle1 = new JButton("Get Solved Puzzle");
        btnPuzzle2 = new JButton("Get Unsolved Puzzle");
        image = new JLabel();

        // Add buttons to content pane
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(btnPuzzle1);
        container.add(btnPuzzle2);
        container.add(image);

        // Add action listeners to buttons
        btnPuzzle1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadPuzzle(1);
            }
        });

        btnPuzzle2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadPuzzle(2);
            }
        });
    }

    private void downloadPuzzle(int id) {
        try {
            URL url = new URL("http://localhost:8080/puzzle/" + id);
            ImageIcon icon = new ImageIcon(ImageIO.read(url));
            image.setIcon(icon);
            pack();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to download puzzle " + id + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PuzzleClient client = new PuzzleClient();
            client.setVisible(true);
        });
    }
}