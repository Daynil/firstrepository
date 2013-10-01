package projectEuler;

public class Problem9short {
	public static void main(String[] args) {
		for (int z = 997; z >= 335; z--) {
			for (int y = (1000 - z - 1); y + z <= 999 && ((1000 - (y + z)) < y); y--) {
				int x = 1000 - (y + z);
				if (x*x + y*y == z*z) {
					System.out.println(x * y * z);
				}
			}
		}
	}
}