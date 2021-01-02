
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters
{
    public void sortByLastWordInTitleThenByMagnitude()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        printTest(list, 500);
    }
    
    public void sortByTitleAndDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        printTest(list, 500);
    }
    
    public void sortWithCompareTo()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        /*for (int i = 0; i < 50; i++)
        {
            System.out.println(list.get(i));
        }*/
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        System.out.print("total Sorting entries is: " + list.size());
    }
    
    private void printTest(ArrayList<QuakeEntry> curQE, int number)
    {
        for (int i = 0; i <= number; i++)
        {
            System.out.println(curQE.get(i));
        }
        int quakeNumber = number;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(curQE.get(quakeNumber));
        System.out.print("TsortByTitleAndDepthotal Sorting number is: " + curQE.size());
    }

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
}
