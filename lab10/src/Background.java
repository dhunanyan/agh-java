import java.awt.*;

public class Background implements XmasShape {
    int x;
    int y;
    Color firstColor;
    Color secondColor;
    double scale;

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,firstColor,0,400, secondColor);
        g2d.setPaint(grad);
        g2d.fillRect(0, 0, 800, 300);
        g2d.drawRect(0, 0, 800, 300);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
        g2d.scale(this.scale,this.scale);
    }
}