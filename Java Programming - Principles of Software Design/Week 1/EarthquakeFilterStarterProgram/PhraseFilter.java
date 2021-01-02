
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    private String phrase;
    private String where;
    
    public PhraseFilter(String curPhrase, String curWhere)
    {
        phrase = curPhrase;
        where = curWhere;
    }
    
    public boolean satisfies(QuakeEntry qe)
    {
        if (stringProcess(where, phrase, qe.getInfo()))
        {
            return true;
        }
        
        return false;
    }
    
    public String getName()
    {
        return "PhraseFilter";
    }
    
    private boolean stringProcess(String where, String phrase, String data)
    {
        if (phrase.equals("start"))
        {
            int index = data.indexOf(where);
            if (index == 0)
            {
                return true;
            }
        }
        else if (phrase.equals("end"))
        {
            int dataLength = data.length();
            int whereLength = where.length();
            int start = dataLength - whereLength;
            String temp = "";
            if (start >= 0)
            {
                temp = data.substring(start, dataLength);
            }
            if (temp.equals(where))
            {
                return true;
            }
        }
        else
        {
            if (data.contains(where))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public void testStringProcess()
    {
        String where = "test";
        String phrase = "end";
        String data = "testtsttst";
        if (stringProcess(where, phrase, data))
        {
            System.out.println("YES");
        }
        else
        {
            System.out.println("NO!");
        }
    }
}
