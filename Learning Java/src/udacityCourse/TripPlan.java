package udacityCourse;

import java.util.ArrayList;

/**
 * A TripPlan represents a trip and holds a collection of city names.
 */
public class TripPlan
{
    // TODO: add instance variable here
	private ArrayList<String> trip;

    /**
     * Constructs an empty trip.
     */
    public TripPlan()
    {
        // TODO: Initialize the instance variable
    	trip = new ArrayList<String>();
    }

    /**
     * Add a city to the list.
     * @param cityName the city to add
     */
    public void addCity(String cityName)
    {
        // TODO: Write code to add a city to the array list instance variable
    	trip.add(cityName);
    }

    /**
     * Returns a string describing the object.
     * @return a string in the format "TripPlan[cityName1,cityName2,...]"
     */
    public String toString()
    {

        return "TripPlan" + trip;
    }

    /**
     * Removes a city form the this trip
     * @param cityName city to remove
     */
    public void removeCity(String cityName)
    {
        // TODO: Write code to remove a city to the array list instance variable
    	int index = trip.indexOf(cityName);
    	trip.remove(index);
    }

    /**
     * Reverses the elements in the itinerary.
     */
    public void reverse()
    {
    	ArrayList<String> reverser = new ArrayList<String>();
    	for (String each : trip)
    	{
    		reverser.add(each);
    	}
    	for (int i = trip.size()-1; i >= 0; i--)
    	{
    		trip.remove(i);
    	}
    	for (int i = reverser.size()-1; i >= 0; i--)
    	{
    		trip.add(reverser.get(i));
    	}
    }
}
