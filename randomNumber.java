/**
* @author Fred Lee
*/

import java.lang.Math;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class randomNumber {
	public static void create(int n) {
		int[][] matrix = new int[n][n];
		try {
			File file = new File("easylife1.in");
			FileOutputStream is = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			w.write(Integer.toString(n));
			w.write("\n");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) {
						w.write("0 ");
						matrix[i][j] = 0;
					} else {
						double temp = Math.random();
						if (temp < 0.3) {
							w.write("0 ");
							matrix[i][j] = 0;
						} else {
							w.write("1 ");
							matrix[i][j] = 1;
						}						
					}
				}
				w.write("\n");
			}
			w.close();
			int[] in = new int[n];
			int[] out = new int[n];
			for (int i = 0; i < n; i++) {
				int count = 0;
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] == 1) {
						count++;
					}
				}
				in[i] = count;
			}
			for (int i = 0; i < n; i++) {
				int count = 0;
				for (int j = 0; j < n; j++) {
					if (matrix[j][i] == 1) {
						count++;
					}
				}
				out[i] = count;
			}
			int count1 = 1;
			int min = in[0];
			for (int i = 0; i < n; i++) {
				if (in[i] < min) {
					min = in[i];
					count1 = 1;
				} else if (in[i] == min) {
					count1 ++;
				}
			}
			int count2 = 1;
			int max = out[0];
			for (int i = 0; i < n; i++) {
				if (out[i] > max) {
					max = out[i];
					count2 = 1;
				} else if (out[i] == max) {
					count2 ++;
				}
			}
			if (count1 > 1 && count2 > 1) {
				System.out.println("DONE");
				System.out.println(count1);
				System.out.println(count2);
			} else {
				System.out.println("FUCK OFF");
			}
			// System.out.println("    in  out");
			// for (int i = 0; i < n; i++) {
			// 	System.out.println(i + ": " + in[i] + " " + out[i]);
			// }
		} catch (IOException e) {
			System.out.println("ERROR");
		}
	}
	
	public static void main(String[] args) {
		randomNumber.create(100);
	}
}