package fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadText {
	public static void main(String[] args) {
		ReadText prog = new ReadText();
		prog.run();
	}
	
	public void run() {
		try {
			File myFile = new File("WriteTest.txt");
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			String curLine = null;
			String[] words = null;
			
			while ((curLine = reader.readLine()) != null) {
				words = curLine.split(" ");
			}
			reader.close();
			
			for (int i = 0; i < words.length; i++) {
				System.out.println(words[i]);
			}
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
