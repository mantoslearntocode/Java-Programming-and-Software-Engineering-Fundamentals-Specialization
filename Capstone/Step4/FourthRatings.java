import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings
{   
    public FourthRatings()
    {
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public FourthRatings(String movieFile, String ratingsFile)
    {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
    }
    
    public int getMovieSize()
    {
        return MovieDatabase.size();
    }
    
    /* ====== description of method: dotProduct ====== */
    // This method should first translate a rating from the scale 0 to 10 to the scale -5 to 5
    // and return the dot product of the ratings of movies that they both rated.
    private double dotProduct(Rater me, Rater r)
    {
        Double myDotProducts = 0.0;
        ArrayList<String> myRatedIds = me.getItemsRated();
        ArrayList<String> rRatedIds = r.getItemsRated();
        for (String myId : myRatedIds)
        {
            for (String rId : rRatedIds)
            {
                if (myId.equals(rId))
                {
                    myDotProducts += (me.getRating(myId) - 5) * (r.getRating(rId) - 5);
                }
            }
        }
        
        return myDotProducts;
    }
    
    /* ====== description of method: getSimilarities ====== */
    /* this method computes a similarity rating for each rater in the RaterDatabase */
    private ArrayList<Rating> getSimilarities(String myID)
    {
        ArrayList<Rating> sortedRatings = new ArrayList<>();
        ArrayList<Rater> otherRaters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(myID);
        if (me == null || me.numRatings() == 0)
        {
            return null;
        }
        
        for (Rater r : otherRaters)
        {
            double rDot = dotProduct(me, r);
            if ( (!r.getID().equals(me.getID())) && (rDot > 0) )
            {
                sortedRatings.add(new Rating(r.getID(), rDot));
            }
        }
        
        Collections.sort(sortedRatings, Collections.reverseOrder());
        return sortedRatings;
    }
    
    /* ====== description of method: getSimilarRatings ====== */
    // This method returns an ArrayList of Ratings for movies and their calculated weighted ratings, in sorted order.
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        ArrayList<Rating> mySimilar = new ArrayList<>();
        
        //  1º For every rater, get their similarity rating to the given parameter rater id.
        ArrayList<Rating> similarityRatings = getSimilarities(id);
        if (similarityRatings == null || similarityRatings.size() == 0)
        {
            return null;
        }
        
        //  2º For each movie, calculate a weighted average movie rating
        for (String movieID : MovieDatabase.filterBy(new TrueFilter()))
        {
            double curWeighted = 0.0;
            int count = 0;
            
            for (int i = 0; i < numSimilarRaters; i++)
            {
                Rating r = similarityRatings.get(i);
                // in similarityRatings, r.getItem() returns rater's id not the movie_id itself.
                String curRaterId = r.getItem();
                Rater curRater = RaterDatabase.getRater(curRaterId);
                Double curRating = curRater.getRating(movieID);
                if (curRating > 0)
                {
                    count ++;
                    //  3º For each of these raters, multiply their similarity rating by the rating they gave that movie.
                    curWeighted += r.getValue() * curRating;
                }
            }
            if (count >= minimalRaters)
            {
                mySimilar.add(new Rating(movieID, curWeighted / count));
            }
        }
        
        Collections.sort(mySimilar, Collections.reverseOrder());
        return mySimilar;
    }
    
    /* ====== description of method: getSimilarRatingsByFilter ====== */
    // rate only those movies match filterCriteria, similar to the previous function.
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> mySimilar = new ArrayList<>();
        
        //  1º For every rater, get their similarity rating to the given parameter rater id.
        ArrayList<Rating> similarityRatings = getSimilarities(id);
    
        //  2º For each movie, calculate a weighted average movie rating
        for (String movieID : MovieDatabase.filterBy(filterCriteria))
        {
            double curWeighted = 0.0;
            int count = 0;
            
            for (int i = 0; i < numSimilarRaters; i++)
            {
                Rating r = similarityRatings.get(i);
                // in similarityRatings, r.getItem() returns rater's id not the movie_id itself.
                String curRaterId = r.getItem();
                Rater curRater = RaterDatabase.getRater(curRaterId);
                Double curRating = curRater.getRating(movieID);
                if (curRating > 0)
                {
                    count ++;
                    //  3º For each of these raters, multiply their similarity rating by the rating they gave that movie.
                    curWeighted += r.getValue() * curRating;
                }
            }
            if (count >= minimalRaters)
            {
                mySimilar.add(new Rating(movieID, curWeighted / count));
            }
        }
        
        Collections.sort(mySimilar, Collections.reverseOrder());
        return mySimilar;
    } 
    
    private double getAverageByID(String movie_id, int minimalRaters)
    {
        int count = 0;
        double total = 0.0;
        
        for (Rater curRater : RaterDatabase.getRaters())
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
        return RaterDatabase.size();
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
}
