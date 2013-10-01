package projectEuler;

import java.util.ArrayList;

//Largest palindromic number that is a product of two 3 digit numbers

public class Problem4 {

	public static void main(String[] args) {
		Problem4 p4 = new Problem4();
		System.out.println(p4.largestPalindrome());
		//System.out.println(p4.isPalindrome(8012108));
	}
	
	public int largestPalindrome() {
		//create a list of palindromes that are the result of a product of 2 3-digit numbers
		ArrayList<Integer> palindromes = new ArrayList<Integer>();
		int largestPalindrome = 0;
		int theNumber = 0;
		
		for (int first = 999; first > 99; first--) {
			for (int second = 999; second > 99; second--) {
				theNumber = first * second;
				if (isPalindrome(theNumber)) {
					palindromes.add(theNumber);
				}
			}
		}
		// find the largest palindrome in the list
		for (int each : palindromes) {
			if (largestPalindrome == 0) {
				largestPalindrome = each;
			}
			else {
				if (each > largestPalindrome) {
					largestPalindrome = each;
				}
			}
		}
		
		return largestPalindrome;
	}
	
	public boolean isPalindrome(int num) {
		String fullNum = "" + num;  
		int numLen = fullNum.length();
		
		if (!(numLen % 2 == 0)) {  // if number length is odd, disregard middle digit (read same both ways)
			fullNum = fullNum.substring(0, numLen / 2) + fullNum.substring((numLen / 2) + 1, numLen);
			numLen = fullNum.length();
		}
		
		// if the first half of the string is equal to the reverse of the second half, it is palindromic
		String firstHalf = fullNum.substring(0, numLen/2);
		String revSecondHalf = "";
		
		for (int i = numLen; i > numLen/2; i--) {
			String digit = fullNum.substring(i-1, i);
			revSecondHalf += digit;
		}
		
		//System.out.println(fullNum + " " + firstHalf + " " + revSecondHalf);
		
		if (firstHalf.equals(revSecondHalf)) {
			return true;
		}
		else {return false;}
	}
}
