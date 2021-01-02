
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace()
    {
        // TODO Auto-generated constructor stub
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes)
    {
        for (int i = 0; i < (quakes.size() - 1); i++)
        {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude())
            {
                return false;
            }
        }
        
        return true;
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in)
    {
       int countSort = 0;
       for (int i = 0; i < in.size(); i++)
       {
           if (checkInSortedOrder(in))
           {
               break;
           }
           
           int minIdx = getSmallestMagnitude(in,i);
           QuakeEntry qi = in.get(i);
           QuakeEntry qmin = in.get(minIdx);
           in.set(i,qmin);
           in.set(minIdx,qi);
           
           countSort += 1;
       }
       
       System.out.println("The total sort time is: " + countSort);
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakes)
    {
        int countSort = 0;
        for (int numberSorted = 0; numberSorted < quakes.size() - 1; numberSorted++)
        {
            if (!checkInSortedOrder(quakes))
            {
                onePassBubbleSort(quakes, numberSorted);
                countSort += 1;
            }
            else
            {
                break;
            }
        }
        System.out.println("The number of passes needed to sort is: " + countSort);
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakes, int numSorted)
    {
        for (int i = 0; i < (quakes.size() - numSorted - 1); i++)
        {
            QuakeEntry iQuake = quakes.get(i);
            QuakeEntry nextQuake = quakes.get(i+1);
            if (iQuake.getMagnitude() > nextQuake.getMagnitude())
            {
                quakes.set(i, nextQuake);
                quakes.set(i+1, iQuake);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakes)
    {
        for (int numberSorted = 0; numberSorted < quakes.size() - 1; numberSorted++)
        {
            onePassBubbleSort(quakes, numberSorted);
        }
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from)
    {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in)
    { 
       for (int i=0; i< in.size(); i++)
       {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
       }
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from)
    {
        int maxIndex = from;
        for (int i = from + 1; i < quakeData.size(); i++)
        {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth())
            {
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in, int sortTimes)
    {
        for (int i = 0; i < sortTimes; i++)
        {
            int curMax = getLargestDepth(in, i);
            swap(in, curMax, i);
        }
    }
    
    private void swap(ArrayList<QuakeEntry> in, int curMax, int curIndex)
    {
        QuakeEntry qIndex = in.get(curIndex);
        QuakeEntry qMax = in.get(curMax);
        in.set(curIndex, qMax);
        in.set(curMax, qIndex);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByMagnitudeWithCheck(list);
        //sortByLargestDepth(list, 70);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        /*for (QuakeEntry qe: list)
        { 
            System.out.println(qe);
        }*/
        
        System.out.println("=== Done ===");
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
