package calculatorproject;

public class CalcScript {
	
	public static double calculate(String first, String second, String operation) {
		double fNum = Double.parseDouble(first);
		double sNum = Double.parseDouble(second);
		double result = 0;
		if (operation.equals("add")) {
			result = fNum + sNum;
		}
		else if (operation.equals("subtract")) {
			result = fNum - sNum;
		}
		else if (operation.equals("divide")) {
			result = fNum / sNum;
		}
		else if (operation.equals("multiply")) {
			result = fNum * sNum;
		}
		
		return result;
	}
}
