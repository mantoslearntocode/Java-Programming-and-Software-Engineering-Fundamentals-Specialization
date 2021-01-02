
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel
{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<String, ArrayList<String>> fcMap;
    
    public MarkovWordTwo()
    {
        myRandom = new Random();
        myOrder = 2;
    }
    
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text)
    {
        myText = text.split("\\s+");
        buildMap();
    }
    
    private void buildMap()
    {
        fcMap = new HashMap<String, ArrayList<String>>();
        int diff = myText.length - myOrder;
        for (int i = 0; i < diff+1; i++)
        {
            String curKey = "";
            for (int k = 0; k < myOrder; k++)
            {
                curKey += myText[i+k];
                curKey += " ";
            }
            
            curKey = curKey.trim();
            String next = "";
            if (i != diff)
            {
                next = myText[i+myOrder];
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
        
        for (String key : fcMap.keySet())
        {
            System.out.print(key + ": ");
            for (int i = 0; i < fcMap.get(key).size(); i++)
            {
                System.out.print(fcMap.get(key).get(i) + " ");
            }
            System.out.println();
        }
        
        int max = getMaximumMapKey();
        System.out.println("The maximum number of keys following a key is " + max);
        
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
        System.out.println();
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
    
    private int getMaximumMapKey()
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
    
    public String getRandomText(int numWords)
    {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        String key = myText[index] + " " + myText[index+1];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++)
        {
            String key_1 = key;
            ArrayList<String> follows = getFollows(key);
            
            if (follows.size() == 0 || follows == null)
            {
                break;
            }
            /* =========== */
            /*for (int i = 0; i < follows.size(); i++)
            {
                System.out.print(follows.get(i) + "\t");
            }
            System.out.println();*/
            /* =========== */
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            int spaceChar = key.indexOf(" ");
            key = key.substring(spaceChar+1) + " " + next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, String target2, int start)
    {
        for (int i = start; i < words.length-1; i++)
        {
            if (target1.equals(words[i]) && target2.equals(words[i+1]))
            {
                return i;
            }
        }
        
        return -1;
    }
    
    public void testIndexOf()
    {
        /*
        String test = "this is just a test yes this is a simple test";
        String[] testText = test.split(" ");
        System.out.println("this starting at 0: " + indexOf(testText, "this", 0));
        System.out.println("this starting at 3: " + indexOf(testText, "this", 3));
        System.out.println("frog starting at 0: " + indexOf(testText, "frog", 5));
        System.out.println("frog starting at 5: " + indexOf(testText, "frog", 5));
        System.out.println("simple starting at 2: " + indexOf(testText, "simple", 2));
        System.out.println("test starting at 5: " + indexOf(testText, "test", 5));
        */
    }
    
    private ArrayList<String> getFollows(String key)
    {
        /*
        ArrayList<String> followResult = new ArrayList<>();
        int start = 0;
        int diff = myText.length - myOrder;
        while (start < diff)
        {
            int index = indexOf(myText, key, start);
            if (index == -1 || index == diff)
            {
                break;
            }
            followResult.add(myText[index+myOrder]);
            start = index + myOrder;
        }
        
        return followResult;
        */
       return fcMap.get(key);
    }
}










