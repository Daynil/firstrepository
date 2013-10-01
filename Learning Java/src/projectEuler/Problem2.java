package projectEuler;

import java.util.ArrayList;

public class Problem2 {
	
	private int sum = 2; //initiate at 2 since skipping first even number 2
	
	public static void main(String[] args) {
		Problem2 p2 = new Problem2();
		p2.run();
	}
	
	public void run() {
		ArrayList<Integer> fib = new ArrayList<Integer>();
		int n = 0;
		fib.add(1);
		fib.add(2);
		//fib 1, 2, 3, 5, 8 ...
		for (int i = 2; n < 4000000; i++) {
			int twoPrevious = fib.get(i-2);
			int previous = fib.get(i-1);
			n = twoPrevious + previous;
			fib.add(n);
			if (n%2 == 0) {
				sum += n;
			}
		}
		System.out.println(sum);
	}
}
