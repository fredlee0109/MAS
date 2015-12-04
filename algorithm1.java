/**
* @author Fred Lee
*/

import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Algorithm1 {
    int[][] table;
    int[][] graph;
    public Algorithm1() {
    }
    /**
    * For visualization of the graph data
    */
    public void visualizeData(int[][] graph) {
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
    public void visualizeTable(int[][] graph) {
           for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[0].length; j++) {
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
    }

    public void setUp() {
        BufferedReader br = null;
        try {
            String sCurrentLine = "";
            br = new BufferedReader(new FileReader("instances/101.in"));

            int count = Integer.parseInt(br.readLine().replaceAll("\\s+",""));

            graph = new int[count][count];
            for (int i = 0; i < count; i++) {
                sCurrentLine = br.readLine().replaceAll("\\s+","");
                for (int j = 0; j < count; j++) {
                    graph[i][j] = Character.getNumericValue(sCurrentLine.charAt(j));
                }
            }
            run(graph);
            // visualizeData(graph);
            // visualizeTable(graph);
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

    public void run(int[][] graph) {
        table = new int[graph.length][2];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                table[i][0] += graph[i][j];
            }
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                table[i][1] += graph[j][i];
            }
        }
        // visualizeData(graph);
        // visualizeTable(table);
    }
    public static void main(String[] args) {
        Algorithm1 algo = new Algorithm1();
        algo.setUp();
    }
}