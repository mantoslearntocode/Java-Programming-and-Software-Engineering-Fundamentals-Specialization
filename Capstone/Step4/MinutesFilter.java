
public class MinutesFilter implements Filter
{
    private double myMinMinutes;
    private double myMaxMinutes;
    
    public MinutesFilter(double min, double max)
    {
    	myMinMinutes = min;
    	myMaxMinutes = max;
    }
    
    @Override
    public boolean satisfies(String id)
    {
    	double myMinute = MovieDatabase.getMinutes(id);
    	
    	return (myMinute >= myMinMinutes && myMinute <= myMaxMinutes);
    }
	
}
