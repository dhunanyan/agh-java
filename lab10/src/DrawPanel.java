import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

    DrawPanel(){
        setBackground(new Color(241,241,241));
//        setOpaque(true);
    }
}