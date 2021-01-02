
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel
{   
    public MarkovZero()
    {
        myRandom = new Random();
        keyLength = 0;
        //setRandom(88);
    }
    
    public void setKLength()
    {
        keyLength = 0;
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
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < numChars; k++)
        {
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        
        return sb.toString();
    }
    
    public String toString()
    {
        return ("MarkovModel of order " + keyLength);
    }
}
