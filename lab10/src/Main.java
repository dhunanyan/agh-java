import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Choinka");
        DrawPanel drawPanel = new DrawPanel();

        // Xmas tree
        drawPanel.shapes.addAll((new Tree()).branches);

        // Xmas tree decorations
        drawPanel.shapes.addAll((new Decorations()).decorations);

        frame.setContentPane(drawPanel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
