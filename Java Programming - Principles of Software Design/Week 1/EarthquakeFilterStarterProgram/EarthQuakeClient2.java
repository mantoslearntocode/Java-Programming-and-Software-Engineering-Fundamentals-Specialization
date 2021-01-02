import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        // Filter f = new MinMagFilter(4.0);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0, 5.0));
        //maf.addFilter(new DepthFilter(-180000.0, -30000.0));
        
        //Location toLoc = new Location(35.42, 139.43);   // Tokyo,Japan
        //Location toLoc = new Location(36.1314, -95.9372); // Tulsa, Oklahoma
        //Location toLoc = new Location(39.7392, -104.9903); // Denver, Colorado
        Location toLoc = new Location(55.7308, 9.1153); //Billund, Denmark
        maf.addFilter(new DistanceFilter(toLoc, 3000));
        maf.addFilter(new PhraseFilter("Any", "e"));
        
        ArrayList<QuakeEntry> m7  = filter(list, maf);
        printQEData(m7);
        System.out.println("Filters used are: " + maf.getName());
    }
    
    private static void printQEData(ArrayList<QuakeEntry> data)
    {
        for (QuakeEntry qe : data)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + data.size() + " quakes that match that criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
