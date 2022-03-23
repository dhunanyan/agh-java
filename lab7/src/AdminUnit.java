import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    List<AdminUnit> children;
    BoundingBox bbox = new BoundingBox();

    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.printf("%s (minXY[(%f);(%f)], maxXY[(%f);(%f)])\n\t--->admin_level: %d\n\t--->population: %f\n\t--->area: %f\n\t--->density: %f\n\n", this.name, bbox.xmin, bbox.ymin, bbox.xmax, bbox.ymax, this.adminLevel, this.population, this.area, this.density);
        return os.toString(StandardCharsets.UTF_8);
    }

    void fixMissingValues() {
        if (this.parent.density == -1) {
            this.parent.fixMissingValues();
        }
        if (this.density == -1) {
            this.density = this.parent.density;
        }
        if (this.population == -1) {
            this.population = this.area * this.density;
        }
    }
}