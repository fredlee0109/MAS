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

    public HashSet<Integer> getMin(HashSet<Integer> set) {
        HashSet<Integer> minIndexes = new HashSet<Integer>();
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (Integer i : set) {
            for (Integer j : set) {
                if (out.containsKey(i) && out.get(i).contains(j)) {
                    if (count.containsKey(i)) {
                        count.put(i, count.get(i) + 1);
                    } else {
                        count.put(i, 1);
                    }
                }
            }
        }
        int min = 1;
        for (Integer i : count.keySet()) {
            min = i;
            break;
        }
        minIndexes.add(min);
        for (Integer i : count.keySet()) {
            if (count.get(i) == count.get(min)) {
                minIndexes.add(i);
            } else if (count.get(i) < count.get(min)) {
                minIndexes.clear();
                minIndexes.add(i);
                min = i;
            }
        }
        return minIndexes;
    }

    public void algo() {
        HashSet<Integer> totalSet = new HashSet<Integer>();
        for (int i = 0; i < graph.length; i++) {
            totalSet.add(i);
        }
        HashSet<Integer> minIndexes = getMin(totalSet);
        // int min = table[0][0];
        // HashSet<Integer> minIndexes = new HashSet<Integer>();
        // minIndexes.add(0);
        // for (int i = 1; i < table.length; i++) {
        //     if (min == table[i][0]) {
        //         minIndexes.add(i);
        //     } else if (min > table[i][0]) {
        //         minIndexes.clear();
        //         minIndexes.add(i);
        //         min = table[i][0];
        //     }
        // }
        System.out.println("minIndexes is: " + minIndexes);
        if (minIndexes.size() == 1) {
            for (Integer i : minIndexes) {
                q.add(i);
            }
        } else {
            breakTie(minIndexes);
        }
        System.out.print("q is: ");
        System.out.println(q);

        while (!totalSet.isEmpty()) {
            if (totalSet.size() == 1) {
                for (Integer i : totalSet) {
                    q.add(i);
                }
            }
            if (q.isEmpty()) {
                HashSet<Integer> set = getMin(totalSet);
                if (set.size() == 1) {
                    for (Integer i : set) {
                        q.add(i);
                    }
                } else {
                    recursive(set);
                }
            } else {
                int p = q.poll();
                totalSet.remove(p);
                answer.add(p);
                HashSet<Integer> tempSet = out.get(p);
                if (tempSet != null && tempSet.size() > 0) {
                    if (tempSet.size() == 1) {
                        for (Integer i : tempSet) {
                            q.add(i);
                        }
                    } else {
                        recursive(tempSet);
                    }
                }
            }
        }
        for (int i = 0; i < answer.size(); i++) {
            answer.add(answer.poll() + 1);
        }
        System.out.println(answer);
    }

    public void recursive(HashSet<Integer> set) {
        if (set.isEmpty()) {
            return;
        }
        boolean noEdge = true;
        HashMap<Integer, HashSet<Integer>> rin = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> rout = new HashMap<Integer, HashSet<Integer>>();
        for (Integer i : set) {
            for (Integer j : set) {
                if (in.get(i) != null && in.get(i).contains(j)) {
                    noEdge = false;
                    if (rin.containsKey(i)) {
                        rin.get(i).add(j);
                    } else {
                        HashSet<Integer> temp = new HashSet<Integer>();
                        temp.add(j);
                        rin.put(i, temp);
                    }
                }
                if (out.get(i) != null && out.get(i).contains(j)) {
                    noEdge = false;
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
        if (noEdge) {
            Queue<Integer> rq = new LinkedList<Integer>();
            for (Integer i : set) {
                rq.add(i);
            }
            HashMap<Integer, Integer> chartMap = new HashMap<Integer, Integer>();
            for (Integer i : set) {
                if (!rout.containsKey(i)) {
                    chartMap.put(i, 0);
                } else {
                    chartMap.put(i, rout.get(i).size());
                }
            }
            rq = sortHashMapByValuesReturnQ(rq, chartMap);
            chartMap = new HashMap<Integer, Integer>();
            for (Integer i : set) {
                if (!rout.containsKey(i)) {
                    chartMap.put(i, 0);
                } else {
                    chartMap.put(i, rin.get(i).size());
                }
            }
            rq = sortHashMapByValuesReturnQ(rq, chartMap);
            for (Integer i : rq) {
                q.add(i);
            }
        } else {
            HashSet<Integer> maxInNodes = new HashSet<Integer>();
            Queue<Integer> rq = new LinkedList<Integer>();
            int firstNode = 1;
            for (Integer i : set) {
                firstNode = i;
                break;
            }
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
            if (maxInNodes.size() == 1) {
                for (Integer i : maxInNodes) {
                    q.add(i);
                    set.remove(i);
                }
                recursive(set);
            } else {
                for (Integer i : maxInNodes) {
                    rq.add(i);
                    set.remove(i);
                }
                HashMap<Integer, Integer> chartMap = new HashMap<Integer, Integer>();
                for (Integer i : set) {
                    chartMap.put(i, rout.get(i).size());
                }
                rq = sortHashMapByValuesReturnQ(rq, chartMap);
                chartMap = new HashMap<Integer, Integer>();
                for (Integer i : set) {
                    chartMap.put(i, rin.get(i).size());
                }
                rq = sortHashMapByValuesReturnQ(rq, chartMap);
                for (Integer i : rq) {
                    q.add(i);
                }
                recursive(set);
            }
        }
    }

    public void breakTie(HashSet<Integer> set) {
        System.out.println("from here");
        System.out.println(set);
        HashMap<Integer, HashSet<Integer>> rin = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> rout = new HashMap<Integer, HashSet<Integer>>();
        for (Integer i : set) {
            for (Integer j : set) {
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
            recursive(maxInNodes);
        } else {
            q.add(firstNode);
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


    public Queue sortHashMapByValuesReturnQ(Queue<Integer> qu, HashMap<Integer, Integer> aMap) {
       List mapKeys = new ArrayList(qu);
       List mapValues = new ArrayList(aMap.values());
       Collections.sort(mapValues);
       Collections.sort(mapKeys);

       Queue<Integer> q = new LinkedList<Integer>();

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
                   q.add((Integer)key);
                   break;
               }

           }

       }
       return q;
    } 
  
    public static void main(String[] args) {
        MAS foo = new MAS(101);
        foo.setUp();
    }
}