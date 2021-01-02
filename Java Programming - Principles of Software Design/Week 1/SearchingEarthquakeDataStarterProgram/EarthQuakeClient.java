import java.util.*;
import edu.duke.*;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class EarthQuakeClient
{
    private ArrayList<QuakeEntry> quakeData;
    public EarthQuakeClient()
    {
        // TODO Auto-generated constructor stub
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        quakeData = parser.read(source);
        System.out.println("read data for " + quakeData.size()+" quakes");
    }
    
    public ArrayList<QuakeEntry> getQuakeData()
    {
        return quakeData;
    }
    
    public EarthQuakeClient(String fileName)
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        quakeData = parser.read(fileName);
        System.out.println("read data for " + quakeData.size()+" quakes");
    }
    
    private boolean stringProcess(String where, String phrase, String data)
    {
        if (phrase.equals("start"))
        {
            int index = data.indexOf(where);
            if (index == 0)
            {
                return true;
            }
        }
        else if (phrase.equals("end"))
        {
            int dataLength = data.length();
            int whereLength = where.length();
            int start = dataLength - whereLength;
            String temp = "";
            if (start >= 0)
            {
                temp = data.substring(start, dataLength);
            }
            if (temp.equals(where))
            {
                return true;
            }
        }
        else
        {
            if (data.contains(where))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public void testStringProcess()
    {
        String where = "test";
        String phrase = "end";
        String data = "testtsttst";
        if (stringProcess(where, phrase, data))
        {
            System.out.println("YES");
        }
        else
        {
            System.out.println("NO!");
        }
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        
        for (QuakeEntry curQE : quakeData)
        {
            String curInfo = curQE.getInfo();
            if (stringProcess(where, phrase, curInfo))
            {
                int iTest = 1;
                answer.add(curQE);
            }
        }
        
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry curQE : quakeData)
        {
            double curDepth = curQE.getDepth();
            if (curDepth > minDepth && curDepth < maxDepth)
            {
                answer.add(curQE);
            }
        }
        
        return answer;
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry curQE : quakeData)
        {
            double curMag = curQE.getMagnitude();
            if (curMag > magMin)
            {
                answer.add(curQE);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // This method should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData
        // that are less than distMax from the location from. 
        for (QuakeEntry curQE : quakeData)
        {
            Location curLocation = curQE.getLocation();
            double distance = curLocation.distanceTo(from) / 1000;
            if (distance < distMax)
            {
                answer.add(curQE);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    
    public void quakesByPhrase()
    {
        String where = "Explosion";
        String phrase = "start";
        ArrayList<QuakeEntry> testList = filterByPhrase(quakeData, where, phrase);
        printQEData(testList);
    }
    
    public void quakesOfDepth()
    {
        ArrayList<QuakeEntry> depBetQE = filterByDepth(quakeData, -10000.0, -8000.0);
        printQEData(depBetQE);
    }

    public void bigQuakes()
    {
        ArrayList<QuakeEntry> bigQE = filterByMagnitude(quakeData, 5.0);
        printQEData(bigQE);
    }

    public void closeToMe()
    {
        // This location is Durham, NC
        /*Location city = new Location(35.988, -78.907);
        
        ArrayList<QuakeEntry> nearQE = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : nearQE)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + nearQE.size() + " quakes that match that criteria");*/
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> nearQE = filterByDistanceFrom(quakeData, 1000, city);
        printQEData(nearQE);
    }
    
    private static void printQEData(ArrayList<QuakeEntry> data)
    {
        for (QuakeEntry qe : data)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + data.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        csvFileWriter csvFile = new csvFileWriter();
        csvFile.writeCSVFile(list, "test_2");
        //dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
