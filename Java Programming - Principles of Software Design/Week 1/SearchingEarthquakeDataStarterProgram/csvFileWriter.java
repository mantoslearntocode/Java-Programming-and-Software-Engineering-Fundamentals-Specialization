
/**
 * Write a description of csvFileWriter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public class csvFileWriter
{
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final Object [] FILE_HEADER = {"Latitude","Longitude","Magnitude","Info"};
    
    public static void writeCSVFile(ArrayList<QuakeEntry> list, String fileName)
    {
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        
        // Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //initialize FileWriter object
            fileWriter = new FileWriter(fileName);
             
            //initialize CSVPrinter object 
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
             
            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);
             
            //Write a new student object list to the CSV file
            for (QuakeEntry qe : list)
            {
                List curQERecord = new ArrayList();
                curQERecord.add(qe.getLocation().getLatitude());
                curQERecord.add(qe.getLocation().getLatitude());
                curQERecord.add(qe.getMagnitude());
                curQERecord.add(qe.getInfo());
                
                csvFilePrinter.printRecord(curQERecord);
            }
 
            System.out.println("CSV file was created successfully !!!");
             
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally{
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }
}
