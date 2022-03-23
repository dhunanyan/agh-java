import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        ClockWithGui clock = new ClockWithGui();

        frame.setContentPane(clock);
        frame.setSize(300, 320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

        clock.new ClockThread().start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, getHeight() / 2);

        //NUMBERS
        for (int i = 1; i <= 12; i++) {
            String text = String.valueOf(i);
            Font font = g2d.getFont();
            FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
            int textWidth = (int)(font.getStringBounds(text, frc).getWidth());
            int textHeight = (int)(font.getStringBounds(text, frc).getHeight());

            System.out.println(i +"height:"+ textHeight);
            System.out.println(i + "width:" + textWidth);
            AffineTransform at = new AffineTransform();
            at.rotate(2 * Math.PI / 12 * i);
            Point2D src = new Point2D.Float(0, -120);
            Point2D trg = new Point2D.Float();
            at.transform(src, trg);
            g2d.drawString(Integer.toString(i), (int) trg.getX() - 5, (int) trg.getY() + 2);
        }

        //LINES
        for (int i = 1; i <= 60; i++) {
            AffineTransform at = g2d.getTransform();
            g2d.rotate(2 * Math.PI / 60 * i);
            g2d.setStroke(new BasicStroke(i % 5 == 0 ? 2 : 1, CAP_ROUND, JOIN_MITER));
            g2d.drawLine(0, -110, 0, -90);
            g2d.setTransform(at);
        }

        //SECOND
        AffineTransform saveAT = g2d.getTransform();
        g2d.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_MITER));
        g2d.rotate(time.getSecond() % 60 * 2 * Math.PI / 60);
        g2d.drawLine(0, 0, 0, -100);
        g2d.setTransform(saveAT);

        //MINUTE
        g2d.setStroke(new BasicStroke(2, CAP_ROUND, JOIN_MITER));
        g2d.rotate(time.getMinute() % 60 * 2 * Math.PI / 60);
        g2d.rotate(time.getSecond() % 360 * 2 * Math.PI / (60 * 60));
        g2d.drawLine(0, 0, 0, -90);
        g2d.setTransform(saveAT);

        //HOUR
        g2d.setStroke(new BasicStroke(5, CAP_ROUND, JOIN_MITER));
        g2d.rotate(time.getHour() % 12 * 2 * Math.PI / 12);
        g2d.rotate(time.getMinute() % 720 * 2 * Math.PI / (12 * 60));
        g2d.rotate(time.getSecond() % 43200 * 2 * Math.PI / (12 * 60 * 60));
        g2d.drawLine(0, 0, 0, -50);
        g2d.setTransform(saveAT);

        //CIRCLES
        g2d.drawOval(-135, -135, 270, 270);
        g2d.fillOval(-8, -8, 16, 16);
    }

    class ClockThread extends Thread {
        @Override
        public void run() {
            while (true) {
                time = LocalTime.now();
                repaint();
            }
        }
    }
}