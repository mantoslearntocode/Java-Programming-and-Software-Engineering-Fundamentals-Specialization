
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface
{
    public void compareMethods()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel eModel = new EfficientMarkovModel(2);
        eModel.setTraining(st);
        eModel.setRandom(42);
        MarkovModel mModel = new MarkovModel(2);
        mModel.setTraining(st);
        mModel.setRandom(42);
        String ste = mModel.getRandomText(1000);
        printOut(ste);
        String stm = eModel.getRandomText(1000);
        printOut(stm);
    }
    
    public void testHashMap()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel testModel = new EfficientMarkovModel(5);
        testModel.setRandom(531);
        testModel.setTraining(st);
        String stNew= testModel.getRandomText(50);
    }
    
    public void runModel(IMarkovModel markov, String text, int size, int seed)
    {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++)
        {
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 5;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
