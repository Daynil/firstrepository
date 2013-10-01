package projectEuler;

public class Problem5 {
	
	public static void main(String[] args) {
		Problem5 p5 = new Problem5();
		System.out.println(p5.smallestMultiple());
	}
	
	public int smallestMultiple() {
		
		boolean found = false;
		int smallestMultiple = 0;
		
		for (int i = 2520; !found; i++) {
			if (isEvenlyDivisible(i)) {
				found = true;
				smallestMultiple = i;
			}
		}
		return smallestMultiple;
	}
	
	public boolean isEvenlyDivisible(int num) {
		
		boolean isIt = true;
		
		for (int i = 2; i <= 20 && isIt; i++) {
			if (!(num % i == 0)) {
				isIt = false;
			}
		}
		
		return isIt;
	}
}
