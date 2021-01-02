
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel
{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> fcMap;
    
    public EfficientMarkovWord(int order)
    {
        myRandom = new Random();
        myOrder = order;
        fcMap = new HashMap<WordGram, ArrayList<String>>();
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
        int diff = myText.length - myOrder;
        for (int i = 0; i < diff; i++)
        {
            // String curKey = "";
            WordGram curKey = new WordGram(myText, i, myOrder);
            // String next = "";
            String next = myText[i+myOrder];
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
        /*
        for (WordGram key : fcMap.keySet())
        {
            System.out.print(key + ": ");
            for (int i = 0; i < fcMap.get(key).size(); i++)
            {
                System.out.print(fcMap.get(key).get(i) + " ");
            }
            System.out.println();
        }
        */
        int max = getMaximumMapKey();
        System.out.println("The maximum number of keys following a key is " + max);
        /*
        System.out.println("Keys that have the largest are: ");
        ArrayList<WordGram> maxList = getMaxArray(max);
        for (int i = 0; i < maxList.size(); i++)
        {
            System.out.print(maxList.get(i));
            if (i != (maxList.size() - 1))
            {
                System.out.print(", ");
            }
        }
        System.out.println();*/
    }
    
    private ArrayList<WordGram> getMaxArray(int max)
    {
        ArrayList<WordGram> list = new ArrayList<>();
        for (WordGram key : fcMap.keySet())
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
        // String maxKey = "";
        for (WordGram key : fcMap.keySet())
        {
            int curSize = fcMap.get(key).size();
            if (curSize > max)
            {
                max = curSize;
                // maxKey = key;
            }
        }
        return max;
    }
    
    public String getRandomText(int numWords)
    {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        /*for (int i = 0; i < myOrder; i++)
        {
            sb.append(key.wordAt(i));
            sb.append(" ");
        }*/
        sb.append(key.toString());
        sb.append(" ");
        for(int k = 0; k < numWords - myOrder; k++)
        {
            String key_1 = key.toString();
            ArrayList<String> follows = getFollows(key);
            
            if (follows == null || follows.size() == 0)
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
            key = key.shiftAdd(next);
            // System.out.println(key);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start)
    {
        // The indexOf method has three parameters, a String array of all the words in the training text named words
        // a WordGram named target, and an integer named start indicating where to start looking for a WordGram
        // match in words. This method should return the first position from start that has words in the array words that
        // match the WordGram target. If there is no such match then return -1.
        int i = start;
        boolean found = false;
        for (; i <= words.length-target.length(); i++)
        {
            found = true;
            for (int k = 0; k < target.length(); k++)
            {
                if (!words[i+k].equals(target.wordAt(k)))
                {
                    found = false;
                    break;
                }
            }
            if (found == true)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    public void testIndexOf()
    {
        String test = "this is just a test yes this is a simple test";
        String[] testText = test.split(" ");
       
        String[] words = test.split("\\s+");
        int size = 2;
        WordGram wg = new WordGram(words, 0, size);
        System.out.println(wg);
        System.out.println("this starting at 0: " + indexOf(testText, wg, 0));
        System.out.println("this starting at 3: " + indexOf(testText, wg, 2));
    }
    
    private ArrayList<String> getFollows(WordGram kGram)
    {
        return fcMap.get(kGram);
        // The getFollows method has one WordGram parameter named kGram.
        // returns an ArrayList of all the single words that immediately follow
        // an instance of the WordGram parameter somewhere in the training text.
        // This method should call indexOf to find these matches.
        /*ArrayList<String> followResult = new ArrayList<>();
        int diff = myText.length - kGram.length();
        int start = 0;
        while (start < diff)
        {
            int index = indexOf(myText, kGram, start);
            if (index == -1 || index == diff)
            {
                break;
            }
            followResult.add(myText[index+myOrder]);
            start = index + myOrder;
        }
        
        return followResult;*/
    }
}










