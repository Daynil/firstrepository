package projectEuler;

public class Problem1 {
	int sum = 0;
	
	public static void main(String[] args) {
		Problem1 p1 = new Problem1();
		p1.run();
	}
	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			if (i%3 == 0 || i%5 == 0) {
				sum += i;
			}
		}
		System.out.println(sum);
	}
}
