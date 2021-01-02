
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner
{
    public void testHashMap()
    {
        String st = "this is just a test yes yes yes yes this is a simple test test";
        MarkovWordOne testModel = new MarkovWordOne();
        testModel.setRandom(200);
        testModel.setTraining(st);
        //String stNew= testModel.getRandomText(50);
    }
    
    public void runModel(IMarkovModel markov, String text, int size)
    { 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++)
        { 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed)
    { 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++)
        { 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov()
    {
        
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        
        //String st = "this is just a test yes this is a simple test";
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 200, 139); 
    }
    
    public void runMarkovTwo()
    {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        
        //String st = "this is just a test yes this is a simple test";
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        runModel(markovWord, st, 200, 832); 
    } 

    private void printOut(String s)
    {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++)
        {
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60)
            {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
