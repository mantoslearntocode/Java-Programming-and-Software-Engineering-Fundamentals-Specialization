import java.util.ArrayList;
import java.util.HashMap;

public class ThirdRatings
{
    private ArrayList<Rater> myRaters;
    private HashMap<String, ArrayList<String>> mrAllMap;
    private HashMap<String, Integer> raterMap;
    private HashMap<String, Integer> mIDIndexMap;
    
    public ThirdRatings()
    {
    	this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile)
    {
    	FirstRatings dataFirst = new FirstRatings();
        myRaters = dataFirst.loadRaters(ratingsFile);
        raterMap = dataFirst.getIndexMap(1);
        mIDIndexMap = dataFirst.getIndexMap(0);
    }
    
    public int getMovieSize()
    {
    	ArrayList<String> movie_ids = MovieDatabase.filterBy(new TrueFilter());
    	return movie_ids.size();
    }
    
    
    private double getAverageByID(String movie_id, int minimalRaters)
    {
    	int count = 0;
    	double total = 0.0;
        for (Rater curRater : myRaters)
        {
        	double curRating = curRater.getRating(movie_id);
        	if (curRating != -1)
        	{
        		count++;
        		total += curRating;
        	}
        }
        
        if (count >= minimalRaters)
        {
        	return total / count;
        }
        
        return 0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> allRatingsByMin = new ArrayList<>();
        ArrayList<String> movie_ids = MovieDatabase.filterBy(new TrueFilter());
        for (String movie_id : movie_ids)
        {
            double curRating_value = getAverageByID(movie_id, minimalRaters);
            if (curRating_value > 0)
            {
                Rating curRating = new Rating(movie_id, curRating_value);
                allRatingsByMin.add(curRating);
            }
        }
    
        return allRatingsByMin;
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
    	ArrayList<Rating> myRatings = new ArrayList<Rating>();
    	ArrayList<String> movie_ids = MovieDatabase.filterBy(filterCriteria);
    	// at least minimalRaters ratings and satisfies the filter criteria
    	for (String movie_id : movie_ids)
        {
            double curRating_value = getAverageByID(movie_id, minimalRaters);
            if (curRating_value > 0)
            {
                Rating curRating = new Rating(movie_id, curRating_value);
                myRatings.add(curRating);
            }
        }
    	
    	return myRatings;
    }
    
    // write a method named getTitle that has one String parameter named id, representing the ID of a movie.
    // This method returns the title of the movie with that ID.
    // If the movie ID does not exist, then this method should return a String indicating the ID was not found.
    /*
    public double getAvgByTitle(String title)
    {
        String movie_id = getID(title);
        if (movie_id.equals("NO SUCH TITLE"))
        {
            return -1;
        }
        return getAverageByID(movie_id, 0);
    }
    */

}
