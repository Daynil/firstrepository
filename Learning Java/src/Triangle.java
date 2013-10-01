// Scanner is a class that takes input and converts it to a specified type or to a String
import java.util.Scanner;

public class Triangle {
	// Create the scanner object and set it to the variable name sc
	// Why 'static'?, Why 'new'?
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args){
		
		//declare variables to hold base and height
		System.out.println("Enter the triangle's base: ");
		double base = sc.nextDouble();
		System.out.println("Enter the triangle's height:");
		double height = sc.nextDouble();
		double preArea = base * height;
		double Area = preArea / 2;
		System.out.println("The area of your triangle is: " + Area);
	}
	
}
