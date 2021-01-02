
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings extends FirstRatings
{
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private HashMap<String, ArrayList<String>> mrAllMap;
    private HashMap<String, Integer> raterMap;
    private HashMap<String, Integer> mIDIndexMap;

    private void buildmrMap()
    {
        mrAllMap = buildmrMapByAll(myMovies, myRaters);
    }
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile)
    {
        FirstRatings dataFirst = new FirstRatings();
        myMovies = dataFirst.loadMovies(movieFile);
        myRaters = dataFirst.loadRaters(ratingsFile);
        raterMap = dataFirst.getIndexMap(1);
        mIDIndexMap = dataFirst.getIndexMap(0);
        buildmrMap();
    }
    
    // Get the number of movies that were read in and stored in the ArrayList of type Movie.
    public int getMovieSize()
    {
        return myMovies.size();
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    // write a private helper method named getAverageByID that has two parameters:
    // a String named id representing a movie ID and an integer named minimalRaters.
    private double getAverageByID(String movie_id, int minimalRaters)
    {
        ArrayList<String> curMovieRaterIds = mrAllMap.get(movie_id);
        if (curMovieRaterIds.size() < minimalRaters)
        {
            return 0.0;
        }

        double totalRating = 0.0;
        for (String curRatorId : curMovieRaterIds)
        {
            int curRaterIndex = raterMap.get(curRatorId);
            double curRating = myRaters.get(curRaterIndex).getRating(movie_id);
            totalRating += curRating;
        }
        
        return totalRating / curMovieRaterIds.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> allRatingsByMin = new ArrayList<>();
        for (String movie_id : mrAllMap.keySet())
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

    // write a method named getTitle that has one String parameter named id, representing the ID of a movie.
    // This method returns the title of the movie with that ID.
    // If the movie ID does not exist, then this method should return a String indicating the ID was not found.

    public String getTitle(String id)
    {
        int movieIndex = mIDIndexMap.get(id);
        return myMovies.get(movieIndex).getTitle();
    }

    public String getID(String title)
    {
        for (Movie curMovie : myMovies)
        {
            if (title.equals(curMovie.getTitle()))
            {
                return curMovie.getID();
            }
        }

        return "NO SUCH TITLE";
    }
    
    public double getAvgByTitle(String title)
    {
        String movie_id = getID(title);
        if (movie_id.equals("NO SUCH TITLE"))
        {
            return -1;
        }
        return getAverageByID(movie_id, 0);
    }
}














