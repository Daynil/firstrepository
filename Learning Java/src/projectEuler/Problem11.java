package projectEuler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class Problem11 {
	
	private int largestProduct = 0;
	
	int[][] dataSet;
	
	public static void main(String[] args) {
		Problem11 p11 = new Problem11();
		p11.run();
	}
	
	public void run() {
		dataSet = importdata("problem11nums.txt");
		
		// Compare all horizontal products
		for (int col = 0; col < dataSet[0].length - 3; col++) {
			for (int line = 0; line < dataSet.length; line++) {
				if (largestProduct == 0) {
					largestProduct = dataSet[line][col] * dataSet[line][col+1] * dataSet[line][col+2] * dataSet[line][col+3];
				} else {
					int currentProduct = dataSet[line][col] * dataSet[line][col+1] * dataSet[line][col+2] * dataSet[line][col+3];
					if (currentProduct > largestProduct) {
						largestProduct = currentProduct;
					}
				}
			}
		}
		
		// Compare all vertical products
		for (int line = 0; line < dataSet.length - 3; line++) {
			for (int col = 0; col < dataSet[0].length; col++) {
				int currentProduct = dataSet[line][col] * dataSet[line+1][col] * dataSet[line+2][col] * dataSet[line+3][col];
				if (currentProduct > largestProduct) {
					largestProduct = currentProduct;
				}
			}
		}
		
		// Consider diagonal down-right
		for (int col = 0; col < dataSet[0].length - 3; col++) {
			for (int line = 0; line < dataSet.length - 3; line++) {
				int currentProduct = dataSet[line][col] * dataSet[line+1][col+1] * dataSet[line+2][col+2] * dataSet[line+3][col+3];
				if (currentProduct > largestProduct) {
					largestProduct = currentProduct;
				}
			}
		}
		
		// Consider diagonal down-left
		for (int col = dataSet[0].length - 1; col > 2; col--) {
			for (int line = 0; line < dataSet.length - 3; line++) {
				int currentProduct = dataSet[line][col] * dataSet[line+1][col-1] * dataSet[line+2][col-2] * dataSet[line+3][col-3];
				if (currentProduct > largestProduct) {
					largestProduct = currentProduct;
				}
			}
		}
		
		// TODO numbers are not correct, figure out where the import went wrong (Try writing data back to a text file and looking)
		System.out.println(dataSet[6][8]);
	}

	public int[][] importdata(String filename) {
		File file;
		FileReader fr = null;
		BufferedReader br = null;
		int[][] data =new int[20][20];
		int lineNum = 0;
		
		try {
			file = new File(filename);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			while (br.readLine() != null) {
				String line;
				line = br.readLine();
				int colNum = 0;
				for (String each : line.split(" ")) {
					int num = Integer.parseInt(each);
					data[lineNum][colNum] = num;
					colNum++;
				}
				lineNum++;
			}
			br.close();
		} catch (Exception e) {e.printStackTrace();}
		return data;
	}

}
