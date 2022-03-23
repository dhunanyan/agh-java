import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println("WITH HEADER");
        CSVReader withHeader = new CSVReader("with-header.csv",";",true);
        while(withHeader.next()){
            int id = withHeader.getInt(0);
            String firstName = withHeader.get(1);
            String lastName = withHeader.get(2);
            String address = withHeader.get(3);
            int house = withHeader.getInt(4);
            int apart = withHeader.getInt(5);
            System.out.printf(Locale.US,"%d %s %s %s %d %d",id, firstName, lastName, address, house, apart);
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("NO HEADER");
        System.out.println();
        CSVReader noHeader = new CSVReader("no-header.csv",";",false);
        while(noHeader.next()){
            int id = noHeader.getInt(0);
            String firstName = noHeader.get(1);
            String lastName = noHeader.get(2);
            String address = noHeader.get(3);
            int house = noHeader.getInt(4);
            int apart = noHeader.getInt(5);
            System.out.printf(Locale.US,"%d %s %s %s %d %d",id, firstName, lastName, address, house, apart);
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("MISSING VALUE");
        System.out.println();
        CSVReader missingValues = new CSVReader("missing-values.csv",";",true);
        while(missingValues.next()){
            int id = missingValues.getInt(0);
            int parent = missingValues.getInt(1);
            String name = missingValues.get(2);
            int admin_level = missingValues.getInt(3);
            int population = missingValues.getInt(4);
            double area = missingValues.getDouble(5);
            double density = missingValues.getDouble(6);

            System.out.printf(Locale.US,"%d %d %s %d %d %f %f",id, parent, name, admin_level, population, area, density);
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("ELEC");
        System.out.println();
        CSVReader elec = new CSVReader("elec.csv",",",true);
        while(elec.next()){
            int date = elec.getInt(0);
            int day = elec.getInt(1);
            double period = elec.getDouble(2);
            double nswprice = elec.getDouble(3);
            double nswdemand = elec.getDouble(4);
            double vicprice = elec.getDouble(5);
            double vicdemand = elec.getDouble(6);
            double transfer = elec.getDouble(7);
            String classs = elec.get(8);

            System.out.printf(Locale.US,"%d %d %f %f %f %f %f %f %s",date, day, period, nswprice, nswdemand, vicprice, vicdemand, transfer, classs);
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("ACCELERATOR");
        System.out.println();
        CSVReader accelerator = new CSVReader("accelerator.csv",";",true);
        while(accelerator.next()){
            double X = accelerator.getDouble(0);
            double Y = accelerator.getDouble(1);
            double Z = accelerator.getDouble(2);
            double longitude = accelerator.getDouble(3);
            double latitude = accelerator.getDouble(4);
            double speed = accelerator.getDouble(5);
            double time = accelerator.getDouble(6);
            double label = accelerator.getDouble(7);

            System.out.printf(Locale.US,"%f %f %f %f %f %f %f %f",X, Y, Z, longitude, latitude, speed, time, label);
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("TEST");
        System.out.println();
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader test = new CSVReader(new StringReader(text),",",true);
        while(test.next()){
            double a = test.getDouble(0);
            double b = test.getDouble(1);
            double c = test.getDouble(2);
            System.out.printf(Locale.US,"%f %f %f",a, b, c);
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println("TITANIC");
        System.out.println();
        CSVReader titanicPart = new CSVReader("titanic-part.csv",",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",true);
        while(titanicPart.next()){
            int PassengerId = titanicPart.getInt(0);
            int Survived = titanicPart.getInt(1);
            int Pclass = titanicPart.getInt(2);
            String Name = titanicPart.get(3);
            String Sex = titanicPart.get(4);
            int Age = titanicPart.getInt(5);
            int SibSp = titanicPart.getInt(6);
            int Parch = titanicPart.getInt(7);
            String Ticket = titanicPart.get(8);
            double Fare = titanicPart.getDouble(9);
            String Cabin = titanicPart.get(10);
            String Embarked = titanicPart.get(11);

            System.out.printf(Locale.US,"%d %d %d %s %s %d %d %d %s %f %s %s",PassengerId, Survived, Pclass, Name, Sex, Age, SibSp, Parch, Ticket, Fare, Cabin, Embarked);
            System.out.println();
        }

        titanicPart.formatAndSave("titanic-part-output.txt");
    }
}
