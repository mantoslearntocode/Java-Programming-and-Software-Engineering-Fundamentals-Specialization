
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> filterList;
    
    public MatchAllFilter()
    {
        filterList = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f)
    {
        filterList.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        for (Filter f : filterList)
        {
            if (!f.satisfies(qe))
            {
                return false;
            }
        }
        
        return true;
    }
    
    public String getName()
    {
        String filters = "";
        for (int i = 0; i < filterList.size(); i++)
        {
            filters += filterList.get(i).getName();
            if (!(i == (filterList.size()-1)))
            {
                filters += " ";
            }
        }
        
        return filters;
    }
}
