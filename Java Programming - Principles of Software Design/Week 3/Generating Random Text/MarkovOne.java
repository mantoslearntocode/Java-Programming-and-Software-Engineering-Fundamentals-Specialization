
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne
{
    private String myText;
    private Random myRandom;
    private int keyLength;
    
    public MarkovOne()
    {
        //myRandom = new Random();
        setRandom(273);
        keyLength = 1;  // MarkovOne, if Markovtwo then change it to 2.
    }
    
    public ArrayList<String> getFollows(String key)
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
    
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s)
    {
        myText = s.trim();
    }
    
    public String getRandomText(int numChars)
    {
        if (myText == null)
        {
            return "";
        }
        StringBuilder ranS = new StringBuilder();
        // get the keyLength(th) letters/key by random index
        int curIndex = myRandom.nextInt(myText.length()-keyLength);
        String curKey = myText.substring(curIndex, curIndex+keyLength);
        ranS.append(curKey);
        
        while (ranS.length() < numChars)
        {
            // get the following pool
            ArrayList<String> nextPool = getFollows(curKey);
            // get the next String/character
            int sizeTemp = nextPool.size();
            if (nextPool.size() == 0)
            {
                break;
            }
            curIndex = myRandom.nextInt(nextPool.size());
            String curString = nextPool.get(curIndex);
            // add the selected letter to ranS
            ranS.append(curString);
            // set key to the next keyLength string(move 1 character further right).
            curKey = curKey.substring(1) + curString;
        }
        
        
        return ranS.toString();
    }
}
