import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings
{
    public void printAverageRatings(FourthRatings testObject)
    {
    	ArrayList<Rating> allRatings = testObject.getAverageRatings(1);
    	
    	printFunction(allRatings);
    }
    
    public void printAverageRatingsByYearAfterAndGenre(FourthRatings testObject)
    {
    	AllFilters myFilter = new AllFilters();
    	Filter filter_1 = new YearAfterFilter(1980);
    	Filter filter_2 = new GenreFilter("Romance");
    	myFilter.addFilter(filter_1);
    	myFilter.addFilter(filter_2);
    	ArrayList<Rating> allRatings = testObject.getAverageRatingsByFilter(1, myFilter);
    	System.out.println("There are " + allRatings.size() + " such movies. ");
    	Collections.sort(allRatings);
    	System.out.println("------------");
    	System.out.println("After sorting:");
    	for (Rating curRating : allRatings)
    	{
    	    System.out.println(curRating.getValue() + " " + MovieDatabase.getYear(curRating.getItem())
    	    + " " + MovieDatabase.getTitle(curRating.getItem()) + "\n\t" + MovieDatabase.getGenres(curRating.getItem()));
    	}
    }
    
    public void printFunction(ArrayList<Rating> allRatings)
    {
    	System.out.println("There are " + allRatings.size() + " such movies to recommend for you. ");
    	// System.out.println("Before sorting:");
    	for (Rating curRating : allRatings)
    	{
    	    System.out.println(curRating.getValue() + " " + MovieDatabase.getTitle(curRating.getItem()));
    	}
    //    	Collections.sort(allRatings);
    //    	System.out.println("------------");
    //    	System.out.println("After sorting:");
    //    	for (Rating curRating : allRatings)
    //    	{
    //    	    System.out.println(curRating.getValue() + " " + MovieDatabase.getTitle(curRating.getItem()));
    //    	}
    }
    
    public void printSimilarRatings()
    {
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	RaterDatabase.initialize("ratings.csv");
    	FourthRatings testObject = new FourthRatings("ratedmovies_short.csv", "ratings_short.csv");
    	System.out.println("There are " + testObject.getRaterSize() + " raters and " + testObject.getMovieSize() + " movies.");
    	ArrayList<Rating> testList = testObject.getSimilarRatings("65", 20, 5);
    	printFunction(testList);
    }
    
    public void printSimilarRatingsByGenre()
    {
    	String genre = "Action";
    	Filter genreFilter = new GenreFilter(genre);
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	RaterDatabase.initialize("ratings.csv");
    	FourthRatings testObject = new FourthRatings();
    	System.out.println("There are " + testObject.getRaterSize() + " raters and " + testObject.getMovieSize() + " movies.");
    	ArrayList<Rating> testList = testObject.getSimilarRatingsByFilter("65", 20, 5, genreFilter);
    	printFunction(testList);
    }
    
    public void printSimilarRatingsByDirector()
    {
    	String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
    	Filter directorFilter = new DirectorsFilter(directors);
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	RaterDatabase.initialize("ratings.csv");
    	FourthRatings testObject = new FourthRatings();
    	System.out.println("There are " + testObject.getRaterSize() + " raters and " + testObject.getMovieSize() + " movies.");
    	ArrayList<Rating> testList = testObject.getSimilarRatingsByFilter("1034", 10, 3, directorFilter);
    	printFunction(testList);
    }
    
    public void printSimilarRatingsByGenreAndMinutes()
    {
    	AllFilters allFilter = new AllFilters();
    	String genre = "Adventure";
    	Filter Filter_1 = new GenreFilter(genre);
    	allFilter.addFilter(Filter_1);
    	Double minMinute = 100.0;
    	Double maxMinute = 200.0;
    	Filter Filter_2 = new MinutesFilter(minMinute, maxMinute);
    	allFilter.addFilter(Filter_2);
    	
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	RaterDatabase.initialize("ratings.csv");
    	FourthRatings testObject = new FourthRatings();
    	System.out.println("There are " + testObject.getRaterSize() + " raters and " + testObject.getMovieSize() + " movies.");
    	ArrayList<Rating> testList = testObject.getSimilarRatingsByFilter("65", 10, 5, allFilter);
    	printFunction(testList);
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes()
    {
    	AllFilters allFilter = new AllFilters();
    	int year = 2000;
    	Filter Filter_1 = new YearAfterFilter(year);
    	allFilter.addFilter(Filter_1);
    	Double minMinute = 80.0;
    	Double maxMinute = 100.0;
    	Filter Filter_2 = new MinutesFilter(minMinute, maxMinute);
    	allFilter.addFilter(Filter_2);
    	
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	RaterDatabase.initialize("ratings.csv");
    	FourthRatings testObject = new FourthRatings();
    	System.out.println("There are " + testObject.getRaterSize() + " raters and " + testObject.getMovieSize() + " movies.");
    	ArrayList<Rating> testList = testObject.getSimilarRatingsByFilter("65", 10, 5, allFilter);
    	printFunction(testList);
    }
    
    public void mainTest()
    {
    	 // printSimilarRatings();
    	// printSimilarRatingsByGenre();
    	// printSimilarRatingsByDirector();
    	// printSimilarRatingsByGenreAndMinutes();
    	printSimilarRatingsByYearAfterAndMinutes();
    }
}
