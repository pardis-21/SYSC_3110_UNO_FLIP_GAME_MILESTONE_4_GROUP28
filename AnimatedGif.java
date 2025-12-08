import javax.swing.*;

public class AnimatedGif extends JPanel {

    private JLabel gifLabel;

    public AnimatedGif(String filename) {
        // Load GIF
        ImageIcon icon = new ImageIcon(filename);

        // Create label
        gifLabel = new JLabel(icon);
        gifLabel.setHorizontalAlignment(JLabel.CENTER);
        gifLabel.setVerticalAlignment(JLabel.CENTER);

        // Add to panel
        this.add(gifLabel);
    }
}
