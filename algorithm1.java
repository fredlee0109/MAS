/**
* @author Fred Lee
*/
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.FileNotFoundException;

public class algorithm1 {
    public static void algo() {
        BufferedReader br = null;
        try {
            String sCurrentLine = "";
            br = new BufferedReader(new FileReader("instances/101.in"));

            int count = Integer.parseInt(br.readLine().replaceAll("\\s+",""));

            char[][] graph = new char[count][count];
            for (int i = 0; i < count; i++) {
                sCurrentLine = br.readLine().replaceAll("\\s+","");
                for (int j = 0; j < count; j++) {
                    graph[i][j] = sCurrentLine.charAt(j);
                }
            }
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count; j++) {
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Erorr");
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        algorithm1.algo();
    }
}