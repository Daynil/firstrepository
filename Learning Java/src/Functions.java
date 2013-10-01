import java.util.Scanner;
public class Functions {

	static Scanner input = new Scanner(System.in);
	public static void main(String[] args){
		inputTest();
	}
	public static void inputTest(){
		String message = input.nextLine();
		if (message.equals("hi")){
			System.out.println("Hello!");
		}
	}
}
