
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>
{
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        String word_1 = getLastWord(q1.getInfo());
        String word_2 = getLastWord(q2.getInfo());
        int comp = word_1.compareTo(word_2);
        if (comp == 0)
        {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return comp;
    }
    
    private String getLastWord(String origWord)
    {
        String newWord = new String(origWord);
        int lastIndex = newWord.lastIndexOf(" ");
        if (lastIndex != -1 && !newWord.equals(" "))
        {
            newWord = newWord.substring(lastIndex+1);
        }
        
        return newWord;
    }
}
