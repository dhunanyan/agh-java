import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    List<String> columnLabels = new ArrayList<>();
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;
    String filename;

    public CSVReader(String filename) {
        try {
            this.filename = filename;
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CSVReader(String filename, String delimiter) {
        this(filename);
        this.filename = filename;
        this.delimiter = delimiter;
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader){
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) {
            parseHeader();
        }
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) {
        this(filename,delimiter);
        this.hasHeader = hasHeader;
        if (hasHeader) {
            parseHeader();
        }
    }

    void parseHeader() {
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }
    }

    boolean next() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            return false;
        }
        if(line == null){
            return false;
        }
        current = line.split(delimiter);
        return true;
    }
    List<String> getColumnLabels(){
        return columnLabels;
    }

    int getRecordLength(){
        return current.length;
    }

    boolean isMissing(int columnIndex){
        return !(columnIndex >= 0 && columnIndex < current.length) || current[columnIndex].equals("");
    }

    boolean isMissing(String columnLabel){
        int columnIndex = columnLabelsToInt.get(columnLabel);
        return !(columnIndex >= 0 && columnIndex < current.length) || current[columnIndex].equals("");
    }


    String get(int columnIndex){
        return isMissing(columnIndex) ? "-1" : current[columnIndex];
    }

    String get(String columnLabel){
        int columnIndex = columnLabelsToInt.get(columnLabel);
        return isMissing(columnIndex) ? "-1" : current[columnIndex];
    }

    int getInt(int columnIndex){
        try{
            return isMissing(columnIndex) ? -1 : Integer.parseInt(current[columnIndex]);
        }catch(Exception err){
            throw err;
        }
    }

    int getInt(String columnLabel){
        try{
            int columnIndex = columnLabelsToInt.get(columnLabel);
            return isMissing(columnIndex) ? -1 :  Integer.parseInt(current[columnIndex]);
        }catch(Exception err){
            throw err;
        }
    }

    double getDouble(int columnIndex){
        try{
            return isMissing(columnIndex) ? -1 :  Double.parseDouble(current[columnIndex]);
        }catch(Exception err){
            throw err;
        }
    }

    double getDouble(String columnLabel){
        try{
            int columnIndex = columnLabelsToInt.get(columnLabel);
            return isMissing(columnIndex) ? -1 :  Double.parseDouble(current[columnIndex]);
        }catch(Exception err){
            throw err;
        }
    }

    long getLong(int columnIndex){
        try{
            return isMissing(columnIndex) ? -1 :  Long.parseLong(current[columnIndex]);
        }catch(Exception err){
            throw err;
        }
    }

    long getLong(String columnLabel){
        try{
            int columnIndex = columnLabelsToInt.get(columnLabel);
            return isMissing(columnIndex) ? -1 :  Long.parseLong(current[columnIndex]);
        }catch(Exception err){
            throw err;
        }
    }

    LocalTime getTime(int columnIndex, String format){
        LocalTime time = LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
        return time;
    }

    LocalTime getTime(String columnLabel, String format){
        int columnIndex = columnLabelsToInt.get(columnLabel);
        LocalTime time = LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
        return time;
    }

    LocalDate getData(int columnIndex, String format){
        LocalDate date = LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
        return date;
    }

    LocalDate getData(String columnLabel, String format){
        int columnIndex = columnLabelsToInt.get(columnLabel);
        LocalDate date = LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
        return date;
    }

    void saveAdminUnitListToFile(String inputString, String outputFile){
        Reader reader = new StringReader(inputString);
        try (BufferedReader input = new BufferedReader(reader)) {
            try (PrintWriter output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "Cp1250"))) {
                for (; ; ) {
                    String line = input.readLine();
                    if (line == null) break;
                    output.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}