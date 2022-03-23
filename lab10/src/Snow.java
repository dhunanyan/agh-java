import java.awt.*;

public class Snow implements XmasShape {
    int x;
    int y;
    Color firstColor;
    Color secondColor;
    double scale;
    int size;

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,firstColor,0,size, secondColor);
        g2d.setPaint(grad);
        g2d.fillOval(0, 0, size, size);
        g2d.drawOval(0,0,size,size);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
        g2d.scale(this.scale,this.scale);
    }
}