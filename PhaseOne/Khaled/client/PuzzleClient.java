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

    public PuzzleClient() {
        setTitle("Puzzle Client");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        btnPuzzle1 = new JButton("Get Puzzle 1");
        btnPuzzle2 = new JButton("Get Puzzle 2");

        // Add buttons to content pane
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(btnPuzzle1);
        container.add(btnPuzzle2);

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
            URL url = new URL("http://localhost:8080/puzzles/puzzle" + id);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream("puzzle" + id + ".jpg");
            Files.copy(inputStream, Path.of("puzzle" + id + ".jpg"));
            JOptionPane.showMessageDialog(this, "Puzzle " + id + " downloaded successfully!");
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