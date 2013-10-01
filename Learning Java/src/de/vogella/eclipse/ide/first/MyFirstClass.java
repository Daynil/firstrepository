package de.vogella.eclipse.ide.first;

public class MyFirstClass {
	// Below are all the 'primitive' types in java (aka not objects) and String is a special case
	public static void main(String[] args) {
		String textvar = "This be me!";
		textvar = "Whaddup";
		byte bitey = 1;
		short shortey = 2;
		int num = 3;
		long number = 33;
		float decimal = (float) 2.2;
		double decy = 2.3;
		char letter = 'a';
		boolean t = true;
		if (t) {
				System.out.println(textvar + bitey + shortey + num + number + decimal + decy + letter);
		}
	}
}
