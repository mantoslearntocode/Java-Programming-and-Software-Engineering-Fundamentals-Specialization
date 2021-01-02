
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    private Location loc;
    private double distance;
    
    public DistanceFilter(Location curloc, double maximumDistance)
    {
        loc = curloc;
        distance = maximumDistance;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        Location curLocation = qe.getLocation();
        double curDist = curLocation.distanceTo(loc) / 1000;
        if (curDist < distance)
        {
            return true;
        }
        
        return false;
    }
    
    public String getName()
    {
        return "distanceFilter";
    }
}
