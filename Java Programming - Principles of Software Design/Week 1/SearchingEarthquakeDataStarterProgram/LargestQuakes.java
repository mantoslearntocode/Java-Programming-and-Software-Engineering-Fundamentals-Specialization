
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class LargestQuakes
{
    private ArrayList<QuakeEntry> quakeData;
    public LargestQuakes()
    {
        // TODO Auto-generated constructor stub
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        quakeData = parser.read(source);
        System.out.println("read data for " + quakeData.size()+" quakes");
    }
    
    public void findLargestQuakes()
    {
        /*int maxMagIndex = indexOfLargest();*/
        
        ArrayList<QuakeEntry> largestTest = getLargest(50);
        printQEData(largestTest);
        /*for (int i = 0; i < largestTest.size(); i++)
        {
            System.out.println("The Largest index is: " + i + "\t and the magnitude is: " + largestTest.get(i).getMagnitude());
        }*/
    }
    
    public ArrayList<QuakeEntry> getLargest(int howMany)
    {
        ArrayList<QuakeEntry> largestList = new ArrayList<>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        if (copy.size() <= howMany)
        {
            return copy;
        }
        
        for (int i = 0; i < howMany; i++)
        {
            int maxIndex = indexOfLargest(copy);
            largestList.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        
        return largestList;
    }
    
    private static void printQEData(ArrayList<QuakeEntry> data)
    {
        for (QuakeEntry qe : data)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + data.size() + " quakes that match that criteria");
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> QEData)
    {
        int maxIndex = 0;
        for (int i = 0; i < QEData.size(); i++)
        {
            if (QEData.get(i).getMagnitude() > QEData.get(maxIndex).getMagnitude())
            {
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
}
