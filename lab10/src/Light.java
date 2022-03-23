import java.awt.*;

public class Light implements XmasShape {
    int x;
    int y;
    Color fillColor;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(this.fillColor);
        g2d.fillOval(0, 0, 5, 5);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
    }
}