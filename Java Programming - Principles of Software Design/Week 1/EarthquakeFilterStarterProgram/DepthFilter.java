
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter
{
    private double min;
    private double max;
    
    public DepthFilter(double minimum, double maximum)
    {
        min = minimum;
        max = maximum;
    }
    
    public boolean satisfies(QuakeEntry qe)
    { 
        double curDepth = qe.getDepth();
        if (curDepth >= min && curDepth <= max)
        {
            return true;
        }
        
        return false;
    }
    
    public String getName()
    {
        return "DepthFilter";
    }
}
