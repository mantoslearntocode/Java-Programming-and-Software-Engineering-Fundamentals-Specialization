import java.util.ArrayList;

public class DirectorsFilter implements Filter
{
	private String myDirectors;
	
	public DirectorsFilter(String directors)
	{
		myDirectors = directors;
	}
	
	@Override
	public boolean satisfies(String id)
	{
		String curDirectors = MovieDatabase.getDirector(id);
		String[] myDirectorList = myDirectors.split(",");
		for (int i = 0; i < myDirectorList.length; i++)
		{
			if (curDirectors.contains(myDirectorList[i]))
			{
				return true;
			}
		}
		
		return false;
	}
}
