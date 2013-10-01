package projectEuler;

import java.util.ArrayList;

/**
 * Finds the 10,001st prime number
 *
 */
public class Problem7 {

	public static void main(String[] args) {
		Problem7 p7 = new Problem7();
		System.out.println(p7.desiredPrime(10001));
	}
	
	public int desiredPrime(int num) {
		// Create an array to hold primes
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		int desiredPrime = 0;
		
		for (int i = 2; primeList.size() < num; i++) {
			if (isPrime(i)) {
				primeList.add(i);
			}
		}
		
		desiredPrime = primeList.get(primeList.size() - 1);
		return desiredPrime;
	}
	
	public boolean isPrime(int num) {
		
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
}
