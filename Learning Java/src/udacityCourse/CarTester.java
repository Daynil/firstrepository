package udacityCourse;

public class CarTester {
	
	public static void main(String[] args)
	{
		Car car = new Car(50); //50 mpg - using the mpg-only constructor
		car.addGas(20);
		car.drive(100);
		System.out.println("Expected gas level: 18");
		System.out.println(car.getGasInTank());
	}

}
