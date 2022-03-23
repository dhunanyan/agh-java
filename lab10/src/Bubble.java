import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color firstColor;
    Color secondColor;

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,firstColor,0,100, secondColor);
        g2d.setPaint(grad);
        // ustaw kolor wypełnienia
        g2d.fillOval(0,0,100,100);
        // ustaw kolor obramowania
        g2d.drawOval(0,0,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x,this.y);
        g2d.scale(this.scale,this.scale);
    }
}