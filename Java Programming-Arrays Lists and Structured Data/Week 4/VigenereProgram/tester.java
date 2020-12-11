
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class tester
{
    public void testbreakAll()
    {
        VigenereBreaker testObject = new VigenereBreaker();
        testObject.breakVigenere();
    }
    
    public void testMostCommonCharIn()
    {
        FileResource fr = new FileResource();
        VigenereBreaker testObject = new VigenereBreaker();
        HashSet<String> dictWords = testObject.readDictionary(fr);
        char letter = testObject.mostCommonCharIn(dictWords);
        System.out.println("The most Common char is: " + letter);
    }
    
    public void testBreakVigenere()
    {
        int keyLength = 4;
        char mostCommon = 'e';
        VigenereBreaker testObject = new VigenereBreaker();
        testObject.breakVigenere(keyLength, mostCommon);
    }
    
    public void testBreakVigenere_2()
    {
        char mostCommon = 'e';
        VigenereBreaker testObject = new VigenereBreaker();
        testObject.breakVigenere(mostCommon);
    }
    
    public void testTryKeyLength()
    {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        VigenereBreaker testObject = new VigenereBreaker();
        int[] test = testObject.tryKeyLength(encrypted, 4, 'e');
        for (int i = 0; i < test.length; i++)
        {
            System.out.println(test[i]);
        }
    }
    
    public void testSlice()
    {
        VigenereBreaker testObject = new VigenereBreaker();
        System.out.println(testObject.sliceString("abcdefghijklm", 0, 3));
        System.out.println(testObject.sliceString("abcdefghijklm", 1, 3));
        System.out.println(testObject.sliceString("abcdefghijklm", 2, 3));
        System.out.println(testObject.sliceString("abcdefghijklm", 0, 4));
        System.out.println(testObject.sliceString("abcdefghijklm", 1, 4));
        System.out.println(testObject.sliceString("abcdefghijklm", 2, 4));
        System.out.println(testObject.sliceString("abcdefghijklm", 3, 4));
        System.out.println(testObject.sliceString("abcdefghijklm", 0, 5));
        System.out.println(testObject.sliceString("abcdefghijklm", 1, 5));
        System.out.println(testObject.sliceString("abcdefghijklm", 2, 5));
        System.out.println(testObject.sliceString("abcdefghijklm", 3, 5));
        System.out.println(testObject.sliceString("abcdefghijklm", 4, 5));
    }
    
    public void testVigenereCipher()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] keySet = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keySet);
        System.out.println("The encrypted message is: ");
        String encrypted = vc.encrypt(message);
        System.out.println(encrypted);
        System.out.println("The decrypted message is: ");
        System.out.println(vc.decrypt(encrypted));
    }
    
    public void testCaesarCracker()
    {
        CaesarCracker ccR_E = new CaesarCracker();
        CaesarCracker ccR_P = new CaesarCracker('a');
        
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(ccR_P.decrypt(message));
    }
    
    public void testCaesarCipher()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        int key = 3;
        CaesarCipher testCC = new CaesarCipher(3);
        String encrypted = testCC.encrypt(message);
        System.out.println("The encrypted message: ");
        System.out.println(encrypted);
        
        System.out.println("The decrypted message: ");
        System.out.println(testCC.decrypt(encrypted));
    }
}
