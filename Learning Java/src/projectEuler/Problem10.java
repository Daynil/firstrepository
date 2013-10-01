package projectEuler;
/**
 * Prime Summation
 * @author Danny
 *
 */

public class Problem10 {
	public static void main(String[] args) {
		Problem10 p10 = new Problem10();
		System.out.println(p10.sumPrimesBelow(2000000));
		//System.out.println(p10.isPrime(1999997));
	}
	
	public long sumPrimesBelow(int num) {
		long sum = 2l;  // Make sum a long - integer limit is ~2 billion, not enough
		for (int i = 3; i < num; i++) {
			if (isPrime(i)) {
				sum += (long) i;
			}
		}
		return sum;
	}
	
	public boolean isPrime(long num) {
		
		if (num%2 == 0) {
			return false;
		}
		
		for (int i = 3; i*i <= num; i+=2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
