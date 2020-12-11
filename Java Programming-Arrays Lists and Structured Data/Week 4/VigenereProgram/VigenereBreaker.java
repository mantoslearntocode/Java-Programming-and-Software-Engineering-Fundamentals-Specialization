import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker
{
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int curMax = 0;
        String maxLang = "";
        String decrypted = "";
        for (String lang : languages.keySet())
        {
            HashSet curLangSet = languages.get(lang);
            char mostCommon = mostCommonCharIn(curLangSet);
            int bestKey = breakForLanguage(encrypted, curLangSet, mostCommon);
            System.out.println("The best key for " + lang + " is: " + bestKey);
            decrypted = getDecrypted(encrypted, bestKey, mostCommon);
            int curCount = countWords(decrypted, curLangSet);
            System.out.println("The best words count for " + lang + " is: " + curCount);
            
            if (curCount > curMax)
            {
                curMax = curCount;
                maxLang = lang;
            }
        }
        
        System.out.println("The best language is: " + maxLang + "; count is: " + curMax);
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] wCount = new int[26];
        for (int i = 0; i < 26; i++)
        {
            wCount[i] = 0;
        }
        for (String word : dictionary)
        {
            addLetter(wCount, word);
        }
        
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < 26; i++)
        {
            if (wCount[i] > max)
            {
                max = wCount[i];
                maxIndex = i;
            }
        }
        
        return alphabet.charAt(maxIndex);
    }
    
    private void addLetter(int[] wCount, String word)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < word.length(); i++)
        {
            char curChar = word.charAt(i);
            int curIndex = alphabet.indexOf(Character.toLowerCase(curChar));
            if (curIndex == -1)
            {
                continue;
            }
            
            wCount[curIndex] += 1;
        }
    }
    
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> words = new HashSet<>();
        for (String word : fr.lines())
        {
            word = word.toLowerCase();
            words.add(word);
        }
        
        return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary)
    {
        int count = 0;
        String[] splitM = message.split("\\W+");
        for (String word : splitM)
        {
            if (dictionary.contains(word.toLowerCase()))
            {
                count += 1;
            }
        }
        
        return count;
    }
    
    public int breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommon)
    {
        int tryMax = 100;
        int curMax = 0;
        int bestKey = 1;
        for (int keyLength = 1; keyLength <= tryMax; keyLength++)
        {
            String decrypted = getDecrypted(encrypted, keyLength, mostCommon);
            int curCount = countWords(decrypted, dictionary);
            if ( curCount > curMax)
            {
                curMax = curCount;
                bestKey = keyLength;
            }
            /*if (keyLength == 38)
            {
                System.out.println("The valid words count of key38 is: " + curCount);
            }*/
        }
        
        //System.out.println("The Best Key is: " + bestKey);
        //System.out.println("Valid Words Count is: " + curMax);
        return bestKey;
        //return getDecrypted(encrypted, bestKey, mostCommon);
    }
    
    public String getDecrypted(String encrypted, int keyLength, char mostCommon)
    {
        int[] keys = tryKeyLength(encrypted, keyLength, mostCommon);
        VigenereCipher newVC = new VigenereCipher(keys);
        return(newVC.decrypt(encrypted));
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices)
    {
        StringBuilder sliced = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices)
        {
            sliced.append(message.charAt(i));
        }
        return sliced.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon)
    {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++)
        {
            String slicedM = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slicedM);
        }
        
        return key;
    }

    public void breakVigenere(int keyLength, char mostCommon)
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] keys = tryKeyLength(message, keyLength, mostCommon);
        VigenereCipher newVC = new VigenereCipher(keys);
        String decrypted = newVC.decrypt(message);
        System.out.println("The decrypted message is: ");
        String[] ss = decrypted.split("\n");
        System.out.println(ss[0]);
        System.out.println("--- ---");
        System.out.println(ss[1]);
        System.out.println(ss[2]);
        System.out.println(ss[3]);
    }
    
    public void breakVigenere(char mostCommon)
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        // read dictionary
        FileResource dict = new FileResource();
        HashSet<String> dictWords = readDictionary(dict);
        //System.out.println(dictWords);
        /*String decrypted = breakForLanguage(message, dictWords, 'e');*/
        /*
        System.out.println("The decrypted message is: ");
        String[] ss = decrypted.split("\n");
        System.out.println(ss[0]);
        System.out.println("--- ---");
        System.out.println(ss[1]);
        System.out.println(ss[2]);
        System.out.println(ss[3]);*/
    }
    
    public void breakVigenere()
    {
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            String langName = f.getName();
            System.out.println("Reading the language: " + langName);
            HashSet<String> dict = readDictionary(fr);
            if (!languages.containsKey(langName))
            {
                languages.put(langName, dict);
            }
        }
        
        System.out.println("Already put all the languages in!");
        FileResource encryptFile = new FileResource();
        String encrypted = encryptFile.asString();
        String decrypted = breakForAllLangs(encrypted, languages);
        System.out.println("The decrypted message is: ");
        String[] ss = decrypted.split("\n");
        System.out.println(ss[0]);
        System.out.println("--- ---");
        System.out.println(ss[1]);
        System.out.println(ss[2]);
        System.out.println(ss[3]);
    }
    
}
