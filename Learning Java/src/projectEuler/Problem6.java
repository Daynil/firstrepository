package projectEuler;

public class Problem6 {
	
	public static void main(String[] args) {
		Problem6 p6 = new Problem6();
		System.out.println(p6.answer(100));
	}
	
	public int answer(int amt) {
		int theAnswer = 0;
		theAnswer = squareOfSum(amt) - sumOfSquares(amt);
		return theAnswer;
	}
	
	public int sumOfSquares(int amt) {
		int result = 0;
		
		for (int i = 1; i <= amt; i++) {
			result += Math.pow(i, 2);
		}
		return result;
	}
	
	public int squareOfSum(int amt) {
		int result = 0;
		
		for (int i = 1; i <= amt; i++) {
			result += i;
		}
		
		return (int) Math.pow(result, 2);
	}
}
