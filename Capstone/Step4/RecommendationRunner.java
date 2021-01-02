
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender
{
    private ArrayList<String> myItems;
    
    public RecommendationRunner()
    {
        myItems = new ArrayList<>();
    }
    
    @Override
    public ArrayList<String> getItemsToRate ()
    {
        myItems.clear();
        ArrayList<String> toRates = new ArrayList<>();
        // It returns a list of strings representing movie IDs that will be used to
        // present movies to the user for them to rate. You can choose how to select movies for this list,
        // for example, you could select recent movies, movies from a specific genre, randomly chosen movies,
        // or something else. The movies returned by this method will be displayed on a web page,
        // so the number of movies you choose to return may affect how long the page takes to load,
        // and how willing users will be to rate the movies. 10-20 movies should be fine to get a good profile
        // of the user’s opinions, but 50 may be too many.
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        //RaterDatabase.initialize("ratings.csv");
        
        AllFilters myFilter = new AllFilters();
        myFilter.addFilter(new GenreFilter("Drama"));
        myFilter.addFilter(new YearAfterFilter(2015));
        toRates = MovieDatabase.filterBy(myFilter);
        // FourthRatings testObject = new FourthRatings();
        Random thisGen = new Random();
        for (int i = 0; i < 10; i++)
        {
            while (true)
            {
                int curIndex = thisGen.nextInt(toRates.size());
                String thisItem = toRates.get(curIndex);
                if (!hasMovie(thisItem, myItems))
                {
                    myItems.add(thisItem);
                    break;
                }
            }
        }
        // myItems = toRates;
        return myItems;
    }
    
    private boolean hasMovie(String curValue, ArrayList<String> arrStr)
    {
        for (String curString : arrStr)
        {
            if (curValue.equals(curString))
            {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public void printRecommendationsFor (String webRaterID)
    {
        /*
        getItemsToRate();
        System.out.println("Size: " + myItems.size());
        int count = 0;
        for (String id : myItems)
        {
            System.out.println(count + ": " + MovieDatabase.getMovie(id) + "; Directors: " + MovieDatabase.getDirector(id));
            count++;
        }
        */
       //MovieDatabase.initialize("ratedmoviesfull.csv");
       //RaterDatabase.initialize("ratings.csv");
       //FourthRatings testObject = new FourthRatings("ratedmovies_short.csv", "ratings_short.csv");
       FourthRatings testObject = new FourthRatings();
       ArrayList<Rating> testList = testObject.getSimilarRatings(webRaterID, 1, 1);
       String myRecommendationHTML = "";
       
       if (testList == null || testList.size() == 0)
       {
           myRecommendationHTML += "<h1>No recommendations for you because of no movies were rated by the number of minimal raters: 1</h1>";
       }
       else
       {
           int count = testList.size();
           if (count > 10)
           {
               count = 10;
           }
           
           myRecommendationHTML += "<style>"
                                    +"table {font-family: arial, sans-serif;"
                                            +"border-collapse: collapse;"
                                            +"width: 100%;}"
                                    +"td, th {border: 1px solid #aaa;}"
                                    +"tr:nth-child(even) {background-color: #d3d3d3;}"
                                    +"img {height: 30px;}"
                                    +"</style>";
           
           if (count == 10)
           {
               myRecommendationHTML += "<h1>The top " + count + " movies recommend for you</h1>";
           }
           else
           {
               myRecommendationHTML += "<h1>We have " + count + " movies recommend for you</h1>";
           }
            
               myRecommendationHTML += "<table>"
                                            +"<tr>"
                                                +"<th>No.</th>"
                                                +"<th>Poster</th>"
                                                +"<th>Movie</th>"
                                                +"<th>Genre</th>"
                                                +"<th>Year</th>"
                                                +"<th>Minutes</th>"
                                            +"</tr>";
                                                
           for (int i = 0; i < count; i++)
           {
               myRecommendationHTML += "<tr>"
                                  +"<td>" + (i+1) + "</td>"
                                  +"<td><img src = \"" + MovieDatabase.getPoster(testList.get(i).getItem()) + "\"></td>"
                                  +"<td>" + MovieDatabase.getTitle(testList.get(i).getItem()) + "</td>"
                                  +"<td>" + MovieDatabase.getGenres(testList.get(i).getItem()) + "</td>"
                                  +"<td>" + MovieDatabase.getYear(testList.get(i).getItem()) + "</td>"
                                  +"<td>" + MovieDatabase.getMinutes(testList.get(i).getItem()) + "</td>"
                                  +"</tr>";
           }
           myRecommendationHTML += "</table>";
                                
           
       }
       System.out.println(myRecommendationHTML);
       // printFunction(testList);
    }
    
    public void printFunction(ArrayList<Rating> allRatings)
    {
    	System.out.println("There are " + allRatings.size() + " such movies to recommend for you. ");
    	for (Rating curRating : allRatings)
    	{
    	    System.out.println(curRating.getValue() + " " + MovieDatabase.getTitle(curRating.getItem()));
    	}
    }
}
