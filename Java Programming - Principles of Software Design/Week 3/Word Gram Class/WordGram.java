
public class WordGram
{
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size)
    {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = setHashCode(myWords);
    }
    
    private int setHashCode(String[] source)
    {
        return this.toString().hashCode();
    }
    
    public int hashCode()
    {
        return myHash;
    }

    public String wordAt(int index)
    {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length()
    {
        return myWords.length;
    }

    public String toString()
    {
        String ret = "";
        for (int i = 0; i < length(); i++)
        {
            ret = ret + myWords[i] + " ";
        }
        // System.out.println();
        return ret.trim();
    }

    public boolean equals(Object o)
    {
        WordGram other = (WordGram) o;
        // Write the method equals that has one parameter of type Object named o.
        // This method returns true if two WordGrams are equal and false otherwise.
        if (myWords.length != other.length())
        {
            return false;
        }
        
        for (int i = 0; i < myWords.length; i++)
        {
            if (!myWords[i].equals(other.wordAt(i)))
            {
                return false;
            }
        }
        
        return true;
    }
    
    public void setWordAt(int index, String newWord)
    {
        myWords[index] = newWord;
    }
    
    /* Still not getting clear why these code not working.
    public WordGram shiftAdd(String word)
    {
        System.out.println("In: " + word);
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end.
        for (int i = 0; i < out.length() - 1; i++)
        {
            out.setWordAt(i, out.wordAt(i+1));
        }
        out.setWordAt(out.length()-1, word);
        System.out.println("out: " + out);
        return out;
    } Still not getting clear why these code not working.*/
    
    public WordGram shiftAdd(String word)
    {	       
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        String[] newWords = new String[this.length()];
        for (int i = 0;i < newWords.length-1; i++)
        {
            newWords[i] = this.wordAt(i+1);
        }
        newWords[newWords.length-1] = word;
        WordGram out = new WordGram(newWords, 0, newWords.length);
        return out;
    }

}