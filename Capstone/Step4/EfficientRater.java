import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {

	private String myID;
	// from movieID to its rating map.
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id)
    {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating)
    {
    	Rating curRating = new Rating(item, rating);
    	myRatings.put(item, curRating);
    }

    public boolean hasRating(String item)
    {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item)
    {
    	if (myRatings.containsKey(item))
    	{
    		return myRatings.get(item).getValue();
    	}
        
        return -1;
    }

    public int numRatings()
    {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated()
    {
        ArrayList<String> list = new ArrayList<String>();
        for(String k : myRatings.keySet())
        {
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }

}
