/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage
{
    public void printAverageRatings()
    {
    	SecondRatings newSecond = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
    	System.out.println("There are " + newSecond.getRaterSize() + " raters and " + newSecond.getMovieSize() + " movies.");
    	
    	ArrayList<Rating> allRatings = newSecond.getAverageRatings(3);
    	System.out.println("There are " + allRatings.size() + " such movies. ");
    	
    	System.out.println("Before sorting:");
    	for (Rating curRating : allRatings)
    	{
    	    System.out.println(curRating.getValue() + " " + newSecond.getTitle(curRating.getItem()));
    	}
    	Collections.sort(allRatings);
    	System.out.println("------------");
    	System.out.println("After sorting:");
    	for (Rating curRating : allRatings)
    	{
    	    System.out.println(curRating.getValue() + " " + newSecond.getTitle(curRating.getItem()));
    	}
    }
    
    public void getAverageRatingOneMovie()
    {
        SecondRatings newSecond = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        // print out the average ratings for a specific movie title, such as  the movie “The Godfather”.
        // then the average for the movie “The Godfather”  would be 9.0.
        String movieTitle = "The Godfather";
        double rating = newSecond.getAvgByTitle(movieTitle);
        System.out.println("The average rating for the movie \"" + movieTitle + "\" is " + rating);
    }
    
    /*
    public static void main(String[] args)
    {
    	MovieRunnerAverage testObject = new MovieRunnerAverage();
    	testObject.printAverageRatings();
    }*/
}
