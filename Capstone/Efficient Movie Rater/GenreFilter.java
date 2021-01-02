
public class GenreFilter implements Filter
{
    private String myGenres;
    
    public GenreFilter(String genre)
    {
    	myGenres = genre;
    }
    
    @Override
    public boolean satisfies(String id)
    {
    	if (MovieDatabase.getGenres(id).contains(myGenres))
    	{
    		return true;
    	}
    	
    	return false;
    }
}
