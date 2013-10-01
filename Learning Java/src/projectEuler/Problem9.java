package projectEuler;

/**
 * The Pythagorean triplet
 * @author Danny
 *
 */
public class Problem9 {
	public static void main(String[] args) {
		Problem9 p9 = new Problem9();
		System.out.println(p9.getAnswer());
	}
	
	public int getAnswer() {
		
		int a = 0;
		int b = 0;
		int c = 0;
		
		for (int z = 997; z >= 335; z--) {
			for (int y = (1000 - z - 1); y + z <= 999 && ((1000 - (y + z)) < y); y--) {
				c = z;
				b = y;
				a = 1000 - (b + c);
				if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
					return (a * b * c);
				}
			}
		}
		return 0;
	}

}
