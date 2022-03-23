
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idToAdminUnit = new HashMap<>();
    Map<AdminUnit, Long> adminUnitToParentId = new HashMap<>();
    Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();

    //READS
    public void read(String filename) {
        CSVReader reader = null;
        reader = new CSVReader(filename, ",", true);

        while (reader != null && reader.next()) {
            AdminUnit adminUnit = new AdminUnit();

            adminUnit.name = reader.get("name");

            try {
                adminUnit.adminLevel = reader.getInt("admin_level");
            } catch (Exception e) {
                adminUnit.adminLevel = -1;
            }

            try {
                adminUnit.population = reader.getInt("population");
            } catch (Exception e) {
                adminUnit.population = -1;
            }

            try {
                adminUnit.area = reader.getDouble("area");
            } catch (Exception e) {
                adminUnit.area = -1;
            }

            try {
                adminUnit.density = reader.getDouble("density");
            } catch (Exception e) {
                adminUnit.density = -1;
            }

            try {
                adminUnit.bbox.xmin = Math.min(
                        Math.min(
                                reader.getDouble("x1"),
                                reader.getDouble("x2")),
                        Math.min(
                                reader.getDouble("x3"),
                                reader.getDouble("x4")));
                adminUnit.bbox.ymin = Math.min(
                        Math.min(
                                reader.getDouble("y1"),
                                reader.getDouble("y2")),
                        Math.min(
                                reader.getDouble("y3"),
                                reader.getDouble("y4")));
                adminUnit.bbox.xmax = Math.max(
                        Math.max(
                                reader.getDouble("x1"),
                                reader.getDouble("x2")),
                        Math.max(
                                reader.getDouble("x3"),
                                reader.getDouble("x4")));
                adminUnit.bbox.ymax = Math.max(
                        Math.max(
                                reader.getDouble("y1"),
                                reader.getDouble("y2")),
                        Math.max(
                                reader.getDouble("y3"),
                                reader.getDouble("y4")));
            } catch (Exception e) {
                adminUnit.bbox.xmin = -1;
                adminUnit.bbox.ymin = -1;
                adminUnit.bbox.xmax = -1;
                adminUnit.bbox.ymax = -1;
            }

            long parentId = -1;
            try {
                parentId = reader.getLong("parent");
            } catch (Exception e) {
                parentId = -1;
            }

            this.idToAdminUnit.put(reader.getLong("id"), adminUnit);

            this.adminUnitToParentId.put(adminUnit, parentId);

            if (!this.parentIdToChildren.containsKey(parentId)) {
                this.parentIdToChildren.put(parentId, new ArrayList<>());
            }

            this.parentIdToChildren.get(parentId).add(adminUnit);

            this.units.add(adminUnit);
        }

        for (AdminUnit unit : this.units) {
            long parentId = this.adminUnitToParentId.get(unit);
            unit.parent = this.idToAdminUnit.getOrDefault(parentId, null);
        }

        for (Map.Entry<Long, AdminUnit> entry : this.idToAdminUnit.entrySet()) {
            entry.getValue().children = this.parentIdToChildren.get(entry.getKey());
        }
    }

    //LISTS
    public void list(PrintStream out) {
        for (AdminUnit unit : this.units) {
            out.print(unit);
        }
    }

    //LISTS
    public void list(PrintStream out, int offset, int limit) {
        for (int i = offset; i < limit; i++) {
            out.print(this.units.get(i));
        }
    }

    //SELECTS BY NAME
    public AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList rets = new AdminUnitList();
        for (AdminUnit unit : this.units) {
            if (regex) {
                if (unit.name.matches(pattern)) {
                    rets.units.add(unit);
                }
            } else {
                if (unit.name.contains(pattern)) {
                    rets.units.add(unit);
                }
            }
        }
        return rets;
    }

    //FIXES MISSING VALUES
    private void fixMissingValues() {
        for (AdminUnit unit : this.units) {
            unit.fixMissingValues();
        }
    }

    //GETS NEIGHBOURS
    public AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) {
        AdminUnitList ret = new AdminUnitList();

        for (AdminUnit _unit : this.units) {
            if (_unit.adminLevel == unit.adminLevel && _unit != unit) {
                if (unit.bbox.intersects(_unit.bbox)) {
                    ret.units.add(_unit);
                } else {
                    try {
                        if (unit.adminLevel == 8 && unit.bbox.distanceTo(_unit.bbox) <= maxdistance) {
                            ret.units.add(_unit);
                        }
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ret;
    }

    //SORT NAME
    AdminUnitList sortInplaceByName (){
        class MyComparator implements Comparator<AdminUnit>{

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return o1.name.compareTo(o2.name);
            }
        }

        MyComparator myComparator = new MyComparator();
        units.sort(myComparator);
        return this;
    }

    //SORT AREA
    public AdminUnitList sortInplaceByArea (){
            Comparator<AdminUnit> myComparator = new Comparator<AdminUnit>() {

                @Override
                public int compare(AdminUnit o1, AdminUnit o2) {
                    return Double.compare(o1.area, o2.area);
                }
            };

        this.units.sort(myComparator);
        return this;
    }

    //SORT POPULATION
    AdminUnitList sortInplaceByPopulation(){
        units.sort((o1, o2) -> Double.compare(o1.population,o2.population));
        return this;
    }

    //SORT GENERAL
    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    //SORT GENERAL COPY
    AdminUnitList sort(Comparator<AdminUnit> cmp){
        // Tworzy wyjœciow¹ listê
        AdminUnitList dupUnits = new AdminUnitList();
        // Kopiuje wszystkie jednostki
        dupUnits.units = this.units;
        // wo³a sortInPlace
        dupUnits.sortInplace(cmp);

        return dupUnits;
    }

    //FILTER
    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList filteredUnits = new AdminUnitList();

        for(AdminUnit x : units){
            if(pred.test(x)){
                filteredUnits.units.add(x);
            }
        }

        return filteredUnits;
    }

    //FILTER LIMIT
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList filteredUnits = new AdminUnitList();
        int i = 1;
        for(AdminUnit x : units){
            if(pred.test(x)){
                filteredUnits.units.add(x);

                if(i == limit){
                    break;
                }
                i++;
            }
        }
        return filteredUnits;
    }

    //FILTER OFFSET LIMIT
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList filteredUnits = new AdminUnitList();
        int i = offset;
        for(AdminUnit x : units){
            if(pred.test(x)){
                filteredUnits.units.add(x);

                if(i == limit){
                    break;
                }
                i++;
            }
        }
        return filteredUnits;
    }
}