
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class MarkovModel extends AbstractMarkovModel
{
    private int keyLength;
    
    public MarkovModel()
    {
        //myRandom = new Random();
        setRandom(365);  // testset using the seed of 25.
        keyLength = 4;  // MarkovFour, if Markovtwo then change it to 2.
    }
    
    public void setKLength()
    {
        keyLength = 4;
    }
    
    public MarkovModel(int keylength)
    {
        //myRandom = new Random();
        setRandom(365);  // testset using the seed of 38.
        keyLength = keylength;
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
    
    public String toString()
    {
        return ("MarkovModel of order " + keyLength);
    }
}
