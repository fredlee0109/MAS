/**
* @author Fred Lee
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Iterator;


public class MAS {

    int[][] table;
    int[][] graph;
    LinkedList<Integer> answer = new LinkedList<Integer>();
    HashMap<Integer, HashSet<Integer>> out = new HashMap<Integer, HashSet<Integer>>();
    HashMap<Integer, HashSet<Integer>> in = new HashMap<Integer, HashSet<Integer>>();
    int inst;
    boolean one = false;
    Queue<Integer> q = new LinkedList<Integer>();
    final int total = 621;

    public MAS() {
        one = false;
    }
    public MAS(int inst) {
        one = true;
        this.inst = inst;
    }
    /**
    * For visualization of the graph data
    */
    public void visualizeData() {
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
    public void visualizeTable() {
           for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[0].length; j++) {
                    System.out.print(table[i][j] + " ");
                }
                System.out.println();
            }
    }

    public void delete_1_cycles() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < i; j++) {
                if (graph[i][j] == 1 && graph[j][i] == 1) {
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                }
            }
        }
    }

    public void setUp() {
        BufferedReader br = null;
        try {
            String sCurrentLine = "";
            br = new BufferedReader(new FileReader("instances/" + inst + ".in"));

            int count = Integer.parseInt(br.readLine().replaceAll("\\s+",""));

            graph = new int[count][count];
            for (int i = 0; i < count; i++) {
                sCurrentLine = br.readLine().replaceAll("\\s+","");
                for (int j = 0; j < count; j++) {
                    graph[i][j] = Character.getNumericValue(sCurrentLine.charAt(j));
                }
            }
            delete_1_cycles();
            makeTable(graph);
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

    public void makeTable(int[][] graph) {
        table = new int[graph.length][2];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 1) {
                    if (out.containsKey(i)) {
                        out.get(i).add(j);
                    } else {
                        HashSet<Integer> temp = new HashSet<Integer>();
                        temp.add(j);
                        out.put(i, temp);
                    }
                    if (in.containsKey(j)) {
                        in.get(j).add(i);
                    } else {
                        HashSet<Integer> temp = new HashSet<Integer>();
                        temp.add(i);
                        in.put(j, temp);
                    }
                }
                table[i][0] += graph[i][j];
                table[i][1] += graph[j][i];
            }
        }
        algo();
    }

    public void algo() {
        int min = table[0][0];
        HashSet<Integer> minIndexes = new HashSet<Integer>();
        minIndexes.add(0);
        for (int i = 1; i < table.length; i++) {
            if (min == table[i][0]) {
                minIndexes.add(i);
            } else if (min > table[i][0]) {
                minIndexes.clear();
                minIndexes.add(i);
                min = table[i][0];
            }
        }
        if (minIndexes.size() > 1) {
            breakTie(minIndexes);
        }
    }

    public void breakTie(HashSet<Integer> set) {
        HashMap<Integer, HashSet<Integer>> rin = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> rout = new HashMap<Integer, HashSet<Integer>>();
        for (Integer i : set) {
            for (Integer j : set) {
                System.out.println(i + " " + j);
                if (in.get(i) != null && in.get(i).contains(j)) {
                    if (rin.containsKey(i)) {
                        rin.get(i).add(j);
                    } else {
                        HashSet<Integer> temp = new HashSet<Integer>();
                        temp.add(j);
                        rin.put(i, temp);
                    }
                }
                if (out.get(i) != null && out.get(i).contains(j)) {
                    if (rin.containsKey(i)) {
                        rin.get(i).add(j);
                    } else {
                        HashSet<Integer> temp = new HashSet<Integer>();
                        temp.add(j);
                        rin.put(i, temp);
                    }
                }
            }
        }
        HashSet<Integer> maxInNodes = new HashSet<Integer>();
        int firstNode = 1;
        Queue<Integer> rq = new LinkedList<Integer>();

        maxInNodes.add(firstNode);
        for (Integer i : rin.keySet()) {
            if (rin.get(i).size() > rin.get(firstNode).size()) {
                maxInNodes.clear();
                maxInNodes.add(i);
                firstNode = i;
            } else if (rin.get(i).size() == rin.get(firstNode).size()) {
                maxInNodes.add(i);
            }
        }
        if (maxInNodes.size() > 1) {
            HashMap<Integer, Integer> chartMap = new HashMap<Integer, Integer>();
            //generate a chartMap mapping from node to number of outs
            for (Integer i : maxInNodes) {
                chartMap.put(i, rout.get(i).size());
            }
            //sort the charMap by its value

            chartMap = sortHashMapByValues(chartMap);

            for (Integer i : chartMap.keySet()) {
                rq.add(i);
            }
            maxInNodes.clear();
        }
    }

    public static LinkedHashMap sortHashMapByValues(HashMap<Integer, Integer> aMap) {
       List mapKeys = new ArrayList(aMap.keySet());
       List mapValues = new ArrayList(aMap.values());
       Collections.sort(mapValues);
       Collections.sort(mapKeys);

       LinkedHashMap sortedMap = new LinkedHashMap();

       Iterator valueIt = mapValues.iterator();
       while (valueIt.hasNext()) {
           Integer val = (Integer)valueIt.next();
           Iterator keyIt = mapKeys.iterator();

           while (keyIt.hasNext()) {
               Object key = keyIt.next();
               Integer comp1 = aMap.get(key);
               Integer comp2 = val;

               if (comp1.equals(comp2)){
                   aMap.remove(key);
                   mapKeys.remove(key);
                   sortedMap.put((Integer)key, (Integer)val);
                   break;
               }

           }

       }
       return sortedMap;
    }    
  

    public static void main(String[] args) {
        MAS foo = new MAS(101);
        foo.setUp();
    }
}