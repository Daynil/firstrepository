package udacityCourse;
// /** .. */ adds doc comments that are viewable in java doc; below is the class comment:
/**
 * A simulated car that consumes gas as it drives.
 * @author Danny
 */

public class Car{
	
	private double milesDriven;
	private double gasInTank;
	private double milesPerGallon;
	//private Picture pic;
	
	// This is the constructor of the class - to allow you to pass parameters when creating an instance
	// of the car class to create unique instances (has same name as class - Car):
	// Car mySmart = new Car(80);  Car myHummer = new Car(5);
	// note: no return types after public (not even void) because a constructor's job is just to init
	// the instance variables	
	/**
	 * Constructs a car with the given fuel efficiency
	 * @param mpg the miles per gallon of the car
	 */
	public Car(double mpg)
	{
		milesDriven = 0;     // This initializes the instance variables
		gasInTank = 0;
		milesPerGallon = mpg;
		//pic = new Picture("car.jpg");
		//pic.draw();
	}
	
	//public Car(double mpg, String pictureFile) // Can have more than 1 initializer! 
	//{ 		// This allows you to specify either just mpg and get default pic or mpg+custom pic			
		//milesDriven = 0;     // This initializes the instance variables
		//gasInTank = 0;
		//milesPerGallon = mpg;
		//pic = new Picture(pictureFile);
		//pic.draw();
	//}
	/**
	 * Drives the car a given distance
	 * @param distance how far to drive the car
	 */
	// this.x is an optional reference to an INSTANCE variable - local variables and parameters
	// would not have a this.x reference - this is optional because it is implicit
	// However it can make things more explicit/clear if desired - or if you want to assign a
	// parameter value to an instance variable of the same name (this.name = name)
	public void drive(double distance)
	{
		this.milesDriven = this.milesDriven + distance;
		double gasConsumed = distance / this.milesPerGallon;
		this.gasInTank = this.gasInTank - gasConsumed;
	}
	/**
	 * Adds given gas
	 * @param amount how much to add
	 */
	public void addGas(double amount)
	{
		gasInTank = gasInTank + amount;
	}
	/**
	 * Gets amount of miles driven
	 * @return the current amount of miles driven
	 */
	public double getMilesDriven()
	{
		return milesDriven;
	}
	/**
	 * gets amount of gas in tank
	 * @return the current gas level
	 */
	public double getGasInTank()
	{
		return gasInTank;
	}
}
