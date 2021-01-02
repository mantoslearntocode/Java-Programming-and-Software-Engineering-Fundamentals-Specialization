
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel
{
    protected String myText;
    protected Random myRandom;
    protected int keyLength;
    
    protected ArrayList<String> getFollows(String key)
    {
        ArrayList<String> followResult = new ArrayList<>();
        //for (int i = 0; i < (myText.length() - key.length()); i++)
        int start = 0;
        int diff = myText.length() - key.length();
        while (start < diff)
        {
            int index = myText.indexOf(key, start);
            if (index == -1 || index == diff)
            {
                break;
            }
            followResult.add(myText.substring(index+key.length(), index+key.length()+1));
            start = index + key.length();
        }
        
        return followResult;
    }
    
    public AbstractMarkovModel()
    {
        myRandom = new Random();
    }
    
    public void setTraining(String s)
    {
        myText = s.trim();
    }
    
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);
    abstract public void setKLength();
    abstract public String toString();
}
