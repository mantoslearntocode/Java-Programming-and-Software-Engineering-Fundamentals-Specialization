
/**
 * Write a description of MagnitudeFileter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MagnitudeFilter implements Filter
{
    private double min;
    private double max;
    
    public MagnitudeFilter(double minimum, double maximum)
    {
        min = minimum;
        max = maximum;
    }
    
    public boolean satisfies(QuakeEntry qe)
    { 
        double curMag = qe.getMagnitude();
        if (curMag >= min && curMag <= max)
        {
            return true;
        }
        
        return false;
    }
    
    public String getName()
    {
        return "MagnitudeFilter";
    }
}
