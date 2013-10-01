package fileIO;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteText {
	
	public static void main(String[] args) {
		WriteText prog1 = new WriteText();
		prog1.run();
	}
	
	public void run() {
		try {
			FileWriter fileWriter = new FileWriter("WriteTest.txt");
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write("What is up with you.");
			writer.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
