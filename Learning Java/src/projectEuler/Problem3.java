package projectEuler;


public class Problem3 {
	
	public static void main(String[] args) {
		Problem3 p3 = new Problem3();
		p3.largestPrimeFinder(600851475143l);
	}
	
	public void largestPrimeFinder(long num) {
		System.out.println("Starting...");
		//goal number is 600851475143
		long largestPrime = 0;
		
		for (long i = num - 1; i > 1l; i--) {
			if (num % i == 0) {   //First find a factor of the number
				if (isPrime(i)) {    //Then determine if it is prime; if so, you've found the largest prime
					largestPrime = i;
					break;
				}
			}
		}
		System.out.println("Largest prime: " + largestPrime);
	}
	
	public boolean isPrime(long num) {
		if (num % 2 == 0) {return false;}
		else {
			for (long x = 3l; x*x < num; x += 2) {  
				if(num % x == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
