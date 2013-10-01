package udacityCourse;

public class Substrings
{
    private String wordy;
    //TODO write the constructor and define the instance variable
    public Substrings(String wordy)
    {
        this.wordy = wordy;
    }
    /**
     * Gets all the substrings of this Word.
     * @return all substrings of this Word separated by newline
     */

    public String getSubstrings()
    {
        //TODO complete the stub to return null
        int len = wordy.length();
        String substrings = "";
        for (int i = 0; i < len; i++)
        {
            String piece = "";
            String lastLet = "";
            if (i+1 < len)
            {
                piece += wordy.substring(i, i+1);
                substrings += piece + "\n";
            }
            else
            {
                lastLet += wordy.substring(i, i+1);
                substrings += lastLet + "\n";
            }
        }
        for (int i = 2; i < len; i++)
        {
        	String piece = "";
        	piece += wordy.substring(0, i);
        	substrings += piece + "\n";
        }
        for (int i = 1; i < len-1; i++)
        {
        	String piece = "";
        	piece += wordy.substring(i, len);
        	substrings += piece + "\n";
        }
        return substrings + wordy;
    }
}
