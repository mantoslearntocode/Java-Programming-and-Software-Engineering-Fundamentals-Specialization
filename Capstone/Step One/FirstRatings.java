
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings
{
    private HashMap<String, ArrayList<String>> directorMap; // director to movie_id Map
    private HashMap<String, Integer> mIDIndexMap;	    // for loading distinct movie_id;
    private HashMap<String, Integer> raterMap;              // for loading distinct raterID use.
    private HashMap<String, ArrayList<String>> mrMap;       // movie_id to raters_id Map
    
    public FirstRatings()
    {
        directorMap = new HashMap<String, ArrayList<String>>();
        raterMap = new HashMap<String, Integer>();
        mrMap = new HashMap<String, ArrayList<String>>();
        mIDIndexMap = new HashMap<String, Integer>();
    }
    
    // category: 0 - mIDIndexMap
    // category: 1 - raterMap
    public HashMap<String, Integer> getIndexMap(int category)
    {
        if (category == 0)
        {
            return mIDIndexMap;
        }
        
        return raterMap;
    }
        
    // build movie_id to raters_id Map based on All movies in raterData.
    protected HashMap<String, ArrayList<String>> buildmrMapByAll(ArrayList<Movie> moviedata, ArrayList<Rater> raterData)
    {
        HashMap<String, ArrayList<String>> mapBuilder = new HashMap<>();
        HashMap<String, ArrayList<String>> mrByRaterMap = buildmrMapByRater(raterData);
        for (Movie curMovie : moviedata)
        {
            String curMovieID = curMovie.getID();
            if (mrByRaterMap.containsKey(curMovieID))
            {
                mapBuilder.put(curMovieID, mrByRaterMap.get(curMovieID));
            }
        }
        
        return mapBuilder;
    }
    
    protected HashMap<String, ArrayList<String>> buildmrMapByRater(ArrayList<Rater> raterData)   // build movie_id to raters_id Map based on raterData.
    {
        HashMap<String, ArrayList<String>> mapBuilder = new HashMap<>();
        for (Rater curRater : raterData)
        {
            ArrayList<String> MovieIds = curRater.getItemsRated();
            String curRaterID = curRater.getID();
            for (String curMovieId : MovieIds)
            {
                if (!mapBuilder.containsKey(curMovieId))
                {
                    ArrayList<String> curMRater_list = new ArrayList<>();
                    curMRater_list.add(curRaterID);
                    mapBuilder.put(curMovieId, curMRater_list);
                }
                else
                {
                    mapBuilder.get(curMovieId).add(curRaterID);
                }
            }
        }
        
        return mapBuilder;
    }
    
    public HashMap<String, ArrayList<String>> getDirectorMap()
    {
        return directorMap;
    }
    
    public HashMap<String, ArrayList<String>> getMRMap()
    {
        return mrMap;
    }

    private void buildDirectorMap(ArrayList<Movie> movieData)
    {
        for (Movie data : movieData)
        {
            String[] directorList = Movie.getList(data.getDirector());
            for (int i = 0; i < directorList.length; i++)
            {
                String director = directorList[i];
                if (!directorMap.containsKey(director))
                {
                    ArrayList<String> curMovie = new ArrayList<>();
                    curMovie.add(data.getID());
                    directorMap.put(director, curMovie);
                }
                else
                {
                    directorMap.get(director).add(data.getID());
                }
            }
        }
    }

    public ArrayList<Movie> loadMovies(String filename)
    {
        ArrayList<Movie> dataMovies = new ArrayList<>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        int movieIndex = 0;
        for (CSVRecord record : parser)
        {
            String id = record.get("id");
            String title = record.get("title");;
            String year = record.get("year");
            String genres = record.get("genre");;
            String director = record.get("director");;
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            Movie curMovie = new Movie(id, title, year, genres, director, country, poster, minutes); 
            dataMovies.add(curMovie);
            
            /* === update movie_id to itsIndex in dataMovies ArrayList === */
            mIDIndexMap.put(id, movieIndex);
            movieIndex += 1;

            /* === begin build directorMap === */
            String[] directorList = Movie.getList(curMovie.getDirector());
            for (int i = 0; i < directorList.length; i++)
            {
                String curDirector = directorList[i];
                if (!directorMap.containsKey(curDirector))
                {
                    ArrayList<String> dir_MovieID = new ArrayList<>();
                    dir_MovieID.add(id);
                    directorMap.put(curDirector, dir_MovieID);
                }
                else
                {
                    directorMap.get(curDirector).add(id);
                }
            }
            /* === end build directorMap === */
        }

        return dataMovies;
    }

    private ArrayList<Movie> checkGenres(ArrayList<Movie> moviedata, String genre)
    {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie data : moviedata)
        {
            if (data.getGenres().contains(genre))
            {
                result.add(data);
            }
        }

        return result;
    }

    private ArrayList<Movie> checkMinutes(ArrayList<Movie> moviedata, int minutes)
    {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie data : moviedata)
        {
            if (data.getMinutes() > minutes)
            {
                result.add(data);
            }
        }

        return result;
    }

    public int getMaxNumDirected()
    {
        int maxNum = 0;
        for (String director : directorMap.keySet())
        {
            int curNum = directorMap.get(director).size();
            if (curNum > maxNum)
            {
                maxNum = curNum;
            }
        }
        return maxNum;
    }

    public String[] getNumDirector(int num)
    {
        ArrayList<String> numDirectorList = new ArrayList<>();
        for (String director : directorMap.keySet())
        {
            if (directorMap.get(director).size() == num)
            {
                numDirectorList.add(director);
            }
        }

        return numDirectorList.toArray(new String[numDirectorList.size()]);
    }

    public void testLoadMovies()
    {
        // ArrayList<Movie> dataMovies = loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> dataMovies = loadMovies("ratedmoviesfull.csv");
        System.out.println("The number of movies is: " + dataMovies.size());
        System.out.println("The number of movies including the Comedy genre: " + checkGenres(dataMovies, "Comedy").size());
        System.out.println("The number of movies greater than 150 minutes in length: " + checkMinutes(dataMovies, 150).size());
        int max = getMaxNumDirected();
        System.out.println("The maximum number of movies by any director is: " + max);
        System.out.println("The directors that directed that max number are: ");
        String[] maxDirectorList = getNumDirector(max);
        for (int i = 0; i < maxDirectorList.length-1; i++)
        {
            System.out.print(maxDirectorList[i] + ", ");
        }
        System.out.println(maxDirectorList[maxDirectorList.length - 1]);
    }

    public ArrayList<Rater> loadRaters(String filename)
    {
        ArrayList<Rater> dataRaters = new ArrayList<>();
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        
        int raterIndex = 0; // for the daters index.
        for (CSVRecord record : parser)
        {
            String curId = record.get("rater_id");
            String curMovieId = record.get("movie_id");;
            double curRatingNum = Double.parseDouble(record.get("rating"));
            String curRateTime = record.get("time");
            
            if (!raterMap.containsKey(curId))
            {
            	Rater newRater = new Rater(curId);
            	newRater.addRating(curMovieId, curRatingNum);
            	dataRaters.add(newRater);
            	// update raterMap
            	raterMap.put(curId, raterIndex);
            	raterIndex += 1;
            }
            else // rater already exists.
            {
            	// get the index of this rater int dataRater arraylist.
            	int curRaterIndex = raterMap.get(curId);
            	// then add the rating to the exist rater's rating list.
            	dataRaters.get(curRaterIndex).addRating(curMovieId, curRatingNum);
            	// no need to update raterMap since no new rater inserting
            }
            // insert into movie2rater map
            if (!mrMap.containsKey(curMovieId))
            {
                ArrayList<String> raterId_list = new ArrayList<>();
                raterId_list.add(curId);
                mrMap.put(curMovieId, raterId_list);
            }
            else
            {
                mrMap.get(curMovieId).add(curId);
            }
        }

        return dataRaters;
    }
    
    private int getNumRatingsById(ArrayList<Rater> raterData, String id)
    {
        int idIndex = raterMap.get(id);
        return raterData.get(idIndex).numRatings();
    }
    
    private int getMaxNumRatings(ArrayList<Rater> raterData)
    {
        int maxNum = 0;
        for (Rater curRater : raterData)
        {
            int curNum = curRater.numRatings();
            if (curRater.numRatings() > maxNum)
            {
                maxNum = curNum;
            }
        }
        return maxNum;
    }
    
    private String[] getNumRater(ArrayList<Rater> raterData, int num)
    {
        ArrayList<String> RatorList = new ArrayList<>();
        for (Rater curRater : raterData)
        {
            int curNum = curRater.numRatings();
            if (curNum == num)
            {
                RatorList.add(curRater.getID());
            }
        }

        return RatorList.toArray(new String[RatorList.size()]);
    }
    
    private int getNumMoviesById(ArrayList<Rater> raterData, String movie_id)
    {
        int resultNum = 0;
        for (Rater curRater : raterData)
        {
            if (curRater.hasRating(movie_id))
            {
                resultNum += 1;
            }
        }
        
        return resultNum;
    }
    
    private int getDistinctMovies()
    {
        return mrMap.size();
    }
    
    public void testLoadRators()
    {
    	// ArrayList<Rater> dataRaters = loadRaters("ratings_short.csv");
    	ArrayList<Rater> dataRaters = loadRaters("ratings.csv");
    	System.out.println("There are " + dataRaters.size() + " rators.");
    	/*for (Rater curRator : dataRaters)
    	{
    	    System.out.println("Rator_" + curRator.getID() + ": ");
    	    ArrayList<String> items = curRator.getItemsRated();
    	    for (String movie_id : items)
    	    {
    	        System.out.print(movie_id + ", ");
    	    }
    	    System.out.println();
    	}*/
    	System.out.println("The rator whose rater_id is 193 has " + getNumRatingsById(dataRaters, "193") + " ratings");
    	int max = getMaxNumRatings(dataRaters);
    	System.out.println("The maximum of ratings by any rater is: " + max);
    	String[] raterOfMax = getNumRater(dataRaters, max);
    	System.out.println("There are " + raterOfMax.length + " raters who has such max number of ratings.");
    	System.out.println("The raters that give the max number of rates are: ");
    	for (int i = 0; i < raterOfMax.length-1; i++)
        {
            System.out.print(raterOfMax[i] + ", ");
        }
        System.out.println(raterOfMax[raterOfMax.length - 1]);
    	System.out.println("The number of ratings the movie 1798709 has is: " + getNumMoviesById(dataRaters, "1798709"));
    	System.out.println("The number of movies been rated is: " + getDistinctMovies());
    }
}