import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class BoundingBox {
    double xmin = Double.NaN;
    double ymin = Double.NaN;
    double xmax = Double.NaN;
    double ymax = Double.NaN;

    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.printf("(minXY[(%f);(%f)], maxXY[(%f);(%f)])", this.xmin, this.ymin, this.xmax, this.ymax);
        return os.toString(StandardCharsets.UTF_8);
    }

//Powiêksza BB tak, aby zawiera³ punkt (x,y)
    void addPoint(double x, double y){
        if (Double.isNaN(this.xmin)) {
            this.xmin = x;
        }

        if (Double.isNaN(this.xmax)) {
            this.xmax = x;
        }

        if (Double.isNaN(this.ymin)) {
            this.ymin = y;
        }

        if (Double.isNaN(this.ymax)) {
            this.ymax = y;
        }

        if (x > this.xmax) {
            this.xmax = x;
        } else {
            this.xmin = x;
        }

        if (y > this.ymax) {
            this.ymax = y;
        } else {
            this.ymin = y;
        }
    }

//Sprawdza, czy BB zawiera punkt (x,y)
    boolean contains(double x, double y){
        return x >= this.xmin && x <= this.xmax && y >= this.ymin && y <= this.ymax;
    }

//Sprawdza czy dany BB zawiera bb
    boolean contains(BoundingBox bb){
        return bb.xmin >= this.xmin && bb.xmax <= this.xmax && bb.ymin >= this.ymin && bb.ymax <= this.ymax;
    }

//Sprawdza, czy dany BB przecina siê z bb
    boolean intersects(BoundingBox bb){
        return !(bb.xmin > this.xmax || bb.ymin > this.ymax || this.xmin > bb.xmin || this.ymin > bb.ymax);
    }

//Powiêksza rozmiary tak, aby zawiera³ bb oraz poprzedni¹ wersjê this
    BoundingBox add(BoundingBox bb){
        this.xmin = Math.min(this.xmin, bb.xmin);
        this.ymin = Math.min(this.ymin, bb.ymin);
        this.xmax = Math.max(this.xmax, bb.xmax);
        this.ymax = Math.max(this.ymax, bb.ymax);

        return this;
    }

//Sprawdza czy BB jest pusty
    boolean isEmpty(){
        return Double.isNaN(this.xmin) || Double.isNaN(this.ymin) || Double.isNaN(this.xmax) || Double.isNaN(this.ymax);
    }

//Oblicza i zwraca wspó³rzêdn¹ x œrodka
    double getCenterX(){
        if (!this.isEmpty()) {
            return (this.xmin + this.xmax) / 2;
        } else {
            throw new RuntimeException("BoundingBox is empty!");
        }
    }

//Oblicza i zwraca wspó³rzêdn¹ y œrodka
    double getCenterY(){
        if (!this.isEmpty()) {
            return (this.ymin + this.ymax) / 2;
        } else {
            throw new RuntimeException("BoundingBox is empty!");
        }
    }

//Oblicza odleg³oœæ pomiêdzy œrodkami this bounding box oraz bbx
    double distanceTo(BoundingBox bbx){
        double R = 6372.8;

        double dLat = Math.toRadians(bbx.getCenterY() - this.getCenterY());
        double dLon = Math.toRadians(bbx.getCenterX() - this.getCenterX());
        double lat1 = Math.toRadians(this.getCenterY());
        double lat2 = Math.toRadians(bbx.getCenterY());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}