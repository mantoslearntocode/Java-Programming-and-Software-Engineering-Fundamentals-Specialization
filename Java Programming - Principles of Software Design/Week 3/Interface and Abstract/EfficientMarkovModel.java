
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel
{
    private int keyLength;
    private HashMap<String, ArrayList<String>> fcMap;
    
    private void buildMap()
    {
        fcMap = new HashMap<String, ArrayList<String>>();
        int diff = myText.length() - keyLength;
        for (int i = 0; i < diff+1; i++)
        {
            String curKey = myText.substring(i, i+keyLength);
            String next = "";
            if (i != diff)
            {
                next = myText.substring(i+keyLength, i+keyLength+1);
            } 
            if (fcMap.containsKey(curKey))
            {
                fcMap.get(curKey).add(next);
            }
            else
            {
                ArrayList<String> newTemp = new ArrayList<>();
                newTemp.add(next);
                fcMap.put(curKey, newTemp);
            }
        }
        
        printHashMapInfo();
    }
    
    private void printHashMapInfo()
    {
        //System.out.println("The text is: " + myText);
        System.out.println("It has " + fcMap.size() + " keys in the HashMap");
        int value = 0;
        
        int max = getMaximumKey();
        System.out.println("The maximum number of keys folloing a key is " + max);
        
        System.out.println("Keys that have the largest are: ");
        ArrayList<String> maxList = getMaxArray(max);
        for (int i = 0; i < maxList.size(); i++)
        {
            System.out.print(maxList.get(i));
            if (i != (maxList.size() - 1))
            {
                System.out.print(", ");
            }
        }
    }
    
    private ArrayList<String> getMaxArray(int max)
    {
        ArrayList<String> list = new ArrayList<>();
        for (String key : fcMap.keySet())
        {
            if (fcMap.get(key).size() == max)
            {
                list.add(key);
            }
        }
        
        return list;
    }
    
    private int getMaximumKey()
    {
        int max = 0;
        String maxKey = "";
        for (String key : fcMap.keySet())
        {
            int curSize = fcMap.get(key).size();
            if (curSize > max)
            {
                max = curSize;
                maxKey = key;
            }
        }
        return max;
    }
    
    public EfficientMarkovModel()
    {
        //myRandom = new Random();
        setRandom(365);  // testset using the seed of 365.
        keyLength = 4;  // MarkovFour, if Markovtwo then change it to 2.
        fcMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void setKLength()
    {
        keyLength = 4;
    }
    
    public EfficientMarkovModel(int keylength)
    {
        //myRandom = new Random();
        setRandom(365);  // testset using the seed of 365.
        keyLength = keylength;
        fcMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s)
    {
        myText = s.trim();
        buildMap();
    }
    
    public ArrayList<String> getFollows(int curKey)
    {
        return fcMap.get(curKey);
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
            //buildMap(curKey); // see if the key already built in HashMap, if not build one.
            //
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
        
        //System.out.println("The HashMap size is: " + fcMap.size());
        return ranS.toString();
    }
    
    public String toString()
    {
        return ("EfficientMarkovModel class of order " + keyLength);
    }
}
