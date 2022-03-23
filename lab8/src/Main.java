import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        //WYŒWIETLA WYNIK Z CSVREADERA
        System.out.println("ADMIN UNITS");
        System.out.println();
        CSVReader adminUnits = new CSVReader("admin-units.csv",",",true);
        int i = 0;
        while(adminUnits.next() && i < 5){
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
        System.out.println();
        AdminUnitList adminUnitList = new AdminUnitList();
        try {
            adminUnitList.read("admin-units.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WYŒWIETLA WYNIK (Z ADMIN UNITS)
        int limit = 5;
        int offset = 0;
        System.out.println("ADMIN UNITS");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        adminUnitList.list(ps, offset, limit);
        String result = os.toString();

        System.out.println(result);

        //FILTRUJE WYNIK
        System.out.println("FILTERED ADMIN UNITS");
        ByteArrayOutputStream oss = new ByteArrayOutputStream();
        PrintStream pss = new PrintStream(oss);
        AdminUnitList filterAdminUnitList = adminUnitList.selectByName("Chech", false);
        filterAdminUnitList.list(pss);
        String resultFilter = oss.toString();

        System.out.println(resultFilter);

        //ZAPISUJE WYNIK [SFILTROWANEGO] FORMATU DO PLIKU
        adminUnits.saveAdminUnitListToFile(result, "admin-units-list("+Integer.toString(offset)+"-"+Integer.toString(limit)+").txt");
        adminUnits.saveAdminUnitListToFile(resultFilter, "admin-units-list-filtered.txt");

        //ZNAJDUJÊ JEDNOSTKÊ NA DANYM POZIOMIE (SOWIN)
        System.out.println("ADMIN UNITS - SOWIN");
        ByteArrayOutputStream ossd = new ByteArrayOutputStream();
        PrintStream pssd = new PrintStream(ossd);
        AdminUnitList sowin = adminUnitList.selectByName("^Sowin$", true);
        sowin.list(pssd);
        String winoString = ossd.toString();

        System.out.println(winoString);


        //WYPISUJE LISTÊ S¥SIADÓW DLA WKT
        ByteArrayOutputStream ossdd = new ByteArrayOutputStream();
        PrintStream pssdd = new PrintStream(ossdd);
        AdminUnit unit = sowin.units.get(0);
                double t1 = System.nanoTime() / 1e6;
        AdminUnitList sowinNeighbors = adminUnitList.getNeighbors(unit, 10);
                double t2 = System.nanoTime() / 1e6;
                System.out.printf(Locale.US, "ADMIN UNITS - SOWIN NEIGHBOURS (Searching time: %f)\n", t2 - t1);
        sowinNeighbors.list(pssdd);
        String sowinNeighborsString = ossdd.toString();

        System.out.println(sowinNeighborsString);


        //SOWIN - WKT
        System.out.println("SOWIN - WKT");
        System.out.println(unit.getWKT());
    }
}

