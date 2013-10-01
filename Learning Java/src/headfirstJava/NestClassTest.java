package headfirstJava;

public class NestClassTest {
	
	public static void main(String[] args) {
		
		NestClassTest prog = new NestClassTest();
		prog.go();
	}
	
	public void go() {
		
		System.out.println("Outer Class");
		
		InnerClass progInner = new InnerClass();
		progInner.goInner();
		
	}
	
	class InnerClass {
		
		public void goInner() {
			
			System.out.println("Inner Class");
		
		}
		
	}
	
}
