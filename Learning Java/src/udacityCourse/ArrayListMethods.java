package udacityCourse;

import java.util.ArrayList;
public class ArrayListMethods
{
    ArrayList<String> list; //instance variable
    /**
     * Constructor for objects of class ArrayListMethods
     */
    public ArrayListMethods(ArrayList<String> arrayList)
    {
        // initialise instance variables
        list = arrayList;
    }

    /**
     * Determines if the array list is sorted (do not sort)
     * When Strings are sorted, they are in alphabetical order
     * Use the compareTo method to determine which string comes first
     * You can look at the String compareTo method in the Java API
     * @return true if the array list is sorted else false.
     */
    public boolean isSorted()
    {
        boolean sorted = true;

        // TODO: Determine if the array is sorted.
        if (list.size() == 0)
        {
        	return sorted;
        }
        else
        {
	        String previous = list.get(0);
	        for (String each : list)
	        {
	        	if (previous.compareTo(each) > 0)
	        	{
	        		sorted = false;
	        		break;
	        	}
	        	previous = each;
	        }
        }
        return sorted;
    }

    /**
     * Replaces all but the first and last with the larger of its two neighbors
     * You can use compareTo to determine which string is larger (later in alphabetical
     * order.
     * Example: if the list is originally
     *    ["cat", "ape", "dog", "horse", "zebra"]
     * after this method it should be: 
     *    ["cat", "dog", "horse", "zebra", "zebra"]
     * 
     * @return a string representation of the array list. (do this with list.toString()
     */
    public void replaceWithLargerNeighbor()
    {

        // TODO: Replace all but the first and last elements with the larger of its to neighbors
    	if (list.size() == 0)
    	{
    	}
    	else
    	{
	    	for (int i = 1; i < list.size()-1; i++)
	    	{
	    		String current = list.get(i);
	    		String prev = list.get(i-1);
	    		String next = list.get(i+1);
	    		if (current.compareTo(prev) < 0)
	    		{
	    			list.set(i, prev);
	    			current = list.get(i);
	    		}
	    		if (current.compareTo(next) < 0)
	    		{
	    			list.set(i, next);
	    		}
	    	}
    	}
    }

    /**
     * Gets the number of duplicates in the list.
     * Be careful to only count each duplicate once. Start at index 0. (Does it match any of the other elements?)
     * Get the next word. It is at index i. (Does it match any of the words with index > i?)
     * @return the number of duplicate words in the list
     */
    public int countDuplicates()
    {
        int duplicates = 0;

        // TODO: Write the code to get the number of duplicates in the list
        for (int i = 0; i < list.size()-1; i++)
        {
        	for (int j = i+1; j < list.size(); j++)
        	{
        		String current = list.get(i);
        		String compare = list.get(j);
        		if (current.equals(compare))
        		{
        			duplicates++;
        		}
        	}
        }
        return duplicates;
    }

     /**
     * Moves any word that starts with x, y, or z to the front of the ArrayList, but
     * otherwise preserves the order
     * Example: if the list is orginially
     *   ["ape", "dog", "xantus", "zebra", "cat", "yak"]
     * after this method is called it should be
     *   ["xantus", "zebra", "yak", "ape", "dog", "cat"]
     */
    public void xyzToFront()
    {
        int insertAt = 0;
        ArrayList<String> xyz = new ArrayList<String>();
        // TODO:  Move any word that starts with x, y, or z to the front of the ArrayList, but otherwise preserves the order
        for (int i = list.size()-1; i > 0; i--)
        {
        	String current = list.get(i);
        	if ("xyz".contains(current.substring(0, 1)))
        	{
        		xyz.add(current);
        	}
        	for (String each : xyz)
        	{
        		list.remove(list.indexOf(each));
        		list.add(0, each);
        	}
        }
    }

    /**
     * gets the string representation of this array list
     * @returns the string representation of this array list in
     * standard collection format
     */
    public String toString()
    {
        return list.toString();
    }
}
