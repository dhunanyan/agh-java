import java.awt.*;

public class Trunk implements XmasShape {
    int x;
    int y;
    double scaleX;
    double scaleY;
    double rotate = 0;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.decode("#53350A"));
        g2d.fillRect(0, 0, 120, 160);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
        g2d.scale(this.scaleX, this.scaleY);
        g2d.rotate(Math.toRadians(this.rotate));
    }
}