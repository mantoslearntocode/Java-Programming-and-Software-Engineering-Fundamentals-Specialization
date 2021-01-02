import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters
{
    public static void printAverageRatings(ThirdRatings testObject)
    {
        ArrayList<Rating> allRatings = testObject.getAverageRatings(1);
        
        printFunction(allRatings);
    }
    
    public static void printAverageRatingsByYear(ThirdRatings testObject)
    {   
        Filter yearAfterFilter = new YearAfterFilter(2000);
        ArrayList<Rating> allRatings = testObject.getAverageRatingsByFilter(1, yearAfterFilter);
        printFunction(allRatings);
    }
    
    public static void printAverageRatingsByMinutes(ThirdRatings testObject)
    {
        Filter myFilter = new MinutesFilter(110, 170);
        ArrayList<Rating> allRatings = testObject.getAverageRatingsByFilter(1, myFilter);
        System.out.println("There are " + allRatings.size() + " such movies. ");
        Collections.sort(allRatings);
        System.out.println("------------");
        System.out.println("After sorting:");
        for (Rating curRating : allRatings)
        {
            System.out.println(curRating.getValue() + " Time: " + MovieDatabase.getMinutes(curRating.getItem()) + " " + MovieDatabase.getTitle(curRating.getItem()));
        }
    }
    
    public static void printAverageRatingsByGenre(ThirdRatings testObject)
    {
        Filter myFilter = new GenreFilter("Crime");
        ArrayList<Rating> allRatings = testObject.getAverageRatingsByFilter(1, myFilter);
        System.out.println("There are " + allRatings.size() + " such movies. ");
        Collections.sort(allRatings);
        System.out.println("------------");
        System.out.println("After sorting:");
        for (Rating curRating : allRatings)
        {
            System.out.println(curRating.getValue() + " " + MovieDatabase.getTitle(curRating.getItem()) + "\n\t" + MovieDatabase.getGenres(curRating.getItem()));
        }
    }
    
    public static void printAverageRatingsByDirectors(ThirdRatings testObject)
    {
        Filter myFilter = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> allRatings = testObject.getAverageRatingsByFilter(1, myFilter);
        System.out.println("There are " + allRatings.size() + " such movies. ");
        Collections.sort(allRatings);
        System.out.println("------------");
        System.out.println("After sorting:");
        for (Rating curRating : allRatings)
        {
            System.out.println(curRating.getValue() + " " + MovieDatabase.getTitle(curRating.getItem()) + "\n\t" + MovieDatabase.getDirector(curRating.getItem()));
        }
    }
    
    public static void printAverageRatingsByYearAfterAndGenre(ThirdRatings testObject)
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
    
    public static void printAverageRatingsByDirectorsAndMinutes(ThirdRatings testObject)
    {
        AllFilters myFilter = new AllFilters();
        Filter filter_1 = new MinutesFilter(30, 170);
        Filter filter_2 = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        myFilter.addFilter(filter_1);
        myFilter.addFilter(filter_2);
        ArrayList<Rating> allRatings = testObject.getAverageRatingsByFilter(1, myFilter);
        System.out.println("There are " + allRatings.size() + " such movies. ");
        Collections.sort(allRatings);
        System.out.println("------------");
        System.out.println("After sorting:");
        for (Rating curRating : allRatings)
        {
            System.out.println(curRating.getValue() + " Time:  " + MovieDatabase.getMinutes(curRating.getItem())
            + " " + MovieDatabase.getTitle(curRating.getItem()) + "\n\t" + MovieDatabase.getDirector(curRating.getItem()));
        }
    }
    
    public static void printFunction(ArrayList<Rating> allRatings)
    {
        System.out.println("There are " + allRatings.size() + " such movies. ");
        System.out.println("Before sorting:");
        for (Rating curRating : allRatings)
        {
            System.out.println(curRating.getValue() + " " + MovieDatabase.getTitle(curRating.getItem()));
        }
        Collections.sort(allRatings);
        System.out.println("------------");
        System.out.println("After sorting:");
        for (Rating curRating : allRatings)
        {
            System.out.println(curRating.getValue() + " " + MovieDatabase.getTitle(curRating.getItem()));
        }
    }

    public static void main(String[] args)
    {
        // printAverageRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        
        ThirdRatings testObject = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + testObject.getRaterSize() + " raters and " + testObject.getMovieSize() + " movies.");
        // printAverageRatingsByYear(testObject);
        // printAverageRatingsByGenre(testObject);
        // printAverageRatingsByMinutes(testObject);
        // printAverageRatingsByDirectors(testObject);
        // printAverageRatingsByYearAfterAndGenre(testObject);
        printAverageRatingsByDirectorsAndMinutes(testObject);
    }

}
