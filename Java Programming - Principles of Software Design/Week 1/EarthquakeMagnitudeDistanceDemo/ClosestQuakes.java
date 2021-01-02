
/**
 * Write a description of ClosestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany)
    {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int k = 0; k < howMany; k++)
        {
            int minIndex = 0;
            for (int i = 0; i < copy.size(); i++)
            {
                QuakeEntry curQuake = copy.get(i);
                Location curLocation = curQuake.getLocation();
                if (curLocation. distanceTo(current) < 
                copy.get(minIndex).getLocation().distanceTo(current))
                {
                    minIndex = i;
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        
        return ret;
    }
    
    public void findCloestquakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size());
        Location jakarta = new Location(-6.211, 106.845);
        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 10);
        
        for (int k = 0; k < close.size(); k++)
        {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000.0, close.get(k));
        }
        System.out.println("number found: " + close.size());
    }
}
