import java.awt.*;
import java.awt.geom.Point2D;

public class Branch implements XmasShape {
    double x;
    double y;
    double scale;
    double rotate;
    TriangleShape triangleShape;
    String color;

    public Branch() {
        triangleShape = new TriangleShape(
                new Point2D.Double(160, 0),
                new Point2D.Double(320, 197.6),
                new Point2D.Double(0, 200));
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.decode(color));
        g2d.fill(triangleShape);
        g2d.translate(0, 0);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
        g2d.scale(this.scale, this.scale);
        g2d.rotate(Math.toRadians(this.rotate));
    }
}