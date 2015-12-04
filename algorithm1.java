/**
* @author Fred Lee
*/

import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.FileNotFoundException;

public class algorithm1 {
    /**
    * For visualization of the graph data
    */
    public static void visualizeData(int[][] graph) {
           for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
    }

    /**
    * For visualization of the graph table
    */
    public static void visualizeTable(int[][] graph) {
           for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
    }

    public static void setUp() {
        BufferedReader br = null;
        try {
            String sCurrentLine = "";
            br = new BufferedReader(new FileReader("instances/101.in"));

            int count = Integer.parseInt(br.readLine().replaceAll("\\s+",""));

            int[][] graph = new int[count][count];
            for (int i = 0; i < count; i++) {
                sCurrentLine = br.readLine().replaceAll("\\s+","");
                for (int j = 0; j < count; j++) {
                    graph[i][j] = Character.getNumericValue(sCurrentLine.charAt(j));
                }
            }
            algorithm1.run(graph);
            algorithm1.visualizeData(graph);
            // algorithm1.visualizeTable(graph);
        } catch (IOException e) {
            System.out.println("Error");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void run(int[][] graph) {

    }
    public static void main(String[] args) {
        algorithm1.setUp();
    }
}