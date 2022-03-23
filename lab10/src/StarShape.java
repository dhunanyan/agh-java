import java.awt.*;
import java.awt.geom.GeneralPath;

public class StarShape implements XmasShape {
    private final double points[][] = {
            { 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 },
            { 200, 85 }, { 150, 125 }, { 160, 190 }, { 100, 150 },
            { 40, 190 }, { 50, 125 }, { 0, 85 }
    };

    int x;
    int y;
    double scale;
    Color firstColor;
    Color secondColor;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GeneralPath star = new GeneralPath();
        GradientPaint grad = new GradientPaint(0,0,firstColor,0,100, secondColor);
        g2d.setPaint(grad);

        star.moveTo(points[0][0], points[0][1]);

        for (int k = 1; k < points.length; k++) {
            star.lineTo(points[k][0], points[k][1]);
        }

        star.closePath();
        g2d.fill(star);
        g2d.dispose();
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
        g2d.scale(this.scale, this.scale);
    }
}