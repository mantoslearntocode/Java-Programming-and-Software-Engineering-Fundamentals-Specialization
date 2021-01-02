
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class tester
{
    public void testGetFollowsWithFile()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> testFollow = markov.getFollows("he");
        for (int i = 0; i < testFollow.size(); i++)
        {
            System.out.println(testFollow.get(i));
        }
        System.out.println("There are " + testFollow.size() + " followers to be sellected.");
    }
    
    public void testGetFollows()
    {
        MarkovOne newTest = new MarkovOne();
        String text = "this is a test yes this is a test.";
        newTest.setTraining(text);
        ArrayList<String> testFollow = newTest.getFollows("es");
        for (int i = 0; i < testFollow.size(); i++)
        {
            System.out.println(testFollow.get(i));
        }
        System.out.println("There are " + testFollow.size() + " followers to be sellected.");
    }
}
