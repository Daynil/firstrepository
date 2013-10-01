package udacityCourse;

public class TripPlantester 
{
	public static void main(String[] args)
	{
		TripPlan yo = new TripPlan();
		yo.addCity("Tampa");
		yo.addCity("San Diego");
		yo.addCity("Amsterdam");
		yo.addCity("Munich");
		System.out.println(yo.toString());
		yo.reverse();
		System.out.println(yo.toString());
	}
}
