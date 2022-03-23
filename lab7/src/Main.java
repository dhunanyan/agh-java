import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println("ADMIN UNITS");
        System.out.println();
        CSVReader adminUnits = new CSVReader("admin-units.csv",",",true);
        int i = 0;
        while(adminUnits.next() && i < 100){
            int id = adminUnits.getInt(0);
            int parent = adminUnits.getInt(1);
            String name = adminUnits.get(2);
            int admin_level = adminUnits.getInt(3);
            int population = adminUnits.getInt(4);
            double area = adminUnits.getDouble(5);
            double density = adminUnits.getDouble(6);
            double x1 = adminUnits.getDouble(7);
            double y1 = adminUnits.getDouble(8);
            double x2 = adminUnits.getDouble(9);
            double y2 = adminUnits.getDouble(10);
            double x3 = adminUnits.getDouble(11);
            double y3 = adminUnits.getDouble(12);
            double x4 = adminUnits.getDouble(13);
            double y4 = adminUnits.getDouble(14);

            System.out.printf(Locale.US,"%d %d %s %d %d %f %f %f %f %f %f %f %f %f %f",id, parent, name, admin_level, population, area, density, x1, y1, x2, y2, x3, y3, x4, y4);
            System.out.println();
            i++;
      }

        AdminUnitList adminUnitList = new AdminUnitList();
        try {
            adminUnitList.read("admin-units.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        adminUnitList.list(ps, 1, 100);
        String result = os.toString();

        System.out.println(result);

        adminUnits.saveAdminUnitListToFile(result, "admin-units-list.txt");
    }
}

