import java.util.*;

public class WordGramTester
{
    public void myTestWordGram()
    {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;
        WordGram wg = new WordGram(words, 0, size);
        System.out.println(0 + "\t" + wg.length() + "\t" + wg);
        WordGram newWg = wg.shiftAdd("yes");
        System.out.println(0 + "\t" + newWg.length() + "\t" + newWg);
    }
    
    public void testWordGram()
    {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        int size = 4;
        for(int index = 0; index <= words.length - size; index += 1)
        {
            WordGram wg = new WordGram(words,index,size);
            System.out.println(index+"\t"+wg.length()+"\t"+wg);
        }
    }
    
    public void testWordGramEquals()
    {
        String source = "this is a test this is a test this is a test of words";
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;
        for(int index = 0; index <= words.length - size; index += 1)
        {
            WordGram wg = new WordGram(words,index,size);
            list.add(wg);
        }
        WordGram first = list.get(0);
        System.out.println("checking "+first);
        for(int k=0; k < list.size(); k++)
        {
            if (first.equals(list.get(k)))
            {
                System.out.println("matched at "+k+" "+list.get(k));
            }
        }
    }
}
