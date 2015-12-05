/**
* @author Fred Lee
*/

import java.io.PrintWriter;
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
    LinkedList<Integer> realanswer = new LinkedList<Integer>();
    LinkedList<Integer> answer = new LinkedList<Integer>();
    HashMap<Integer, HashSet<Integer>> out = new HashMap<Integer, HashSet<Integer>>();
    HashMap<Integer, HashSet<Integer>> in = new HashMap<Integer, HashSet<Integer>>();
    int inst;
    boolean one = false;
    Queue<Integer> q = new LinkedList<Integer>();
    final int total = 621;
    HashSet<Integer> totalSet = new HashSet<Integer>();

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
            for (int i = 0; i < graph.length; i++) {
                totalSet.add(i);
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
            count.put(i, 0);
        }
        for (Integer i : set) {
            for (Integer j : set) {
                if (out.containsKey(i) && out.get(i).contains(j)) {
                    count.put(i, count.get(i) + 1);
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
            } else if (!count.containsKey(i) || count.get(i) < count.get(min)) {
                minIndexes.clear();
                minIndexes.add(i);
                min = i;
            }
        }
        return minIndexes;
    }

    public void algo() {
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
        if (minIndexes.size() == 1) {
            for (Integer i : minIndexes) {
                if (totalSet.contains(i)) {
                    q.add(i);
                }
            }
        } else {
            recursive(minIndexes);
        }

        while (!totalSet.isEmpty()) {
            if (totalSet.size() == 1) {
                for (Integer i : totalSet) {
                    if (totalSet.contains(i)) {
                        q.add(i);
                    }
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
                if (totalSet.contains(p)) {
                    answer.add(p);
                    totalSet.remove(p);
                }
                HashSet<Integer> tempSet = out.get(p);
                if (tempSet != null && tempSet.size() > 0) {
                    if (tempSet.size() == 1) {
                        for (Integer i : tempSet) {
                            if (totalSet.contains(i)) {
                                q.add(i);
                            }
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
        Iterator<Integer> it = answer.descendingIterator();
        while (it.hasNext()) {
            realanswer.add(it.next());
        }
        // System.out.println(realanswer);
    }

    public void recursive(HashSet<Integer> set) {
        if (set.isEmpty()) {
            return;
        }
        boolean noEdge = true;
        HashMap<Integer, HashSet<Integer>> rin = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> rout = new HashMap<Integer, HashSet<Integer>>();
        for (Integer i : set) {
            HashSet<Integer> temp = new HashSet<Integer>();
            rin.put(i,temp);
            temp = new HashSet<Integer>();
            rout.put(i, temp);
        }
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
        System.out.println("-----");
        System.out.println(noEdge);
        System.out.println(set);
        System.out.println(rin);
        System.out.println(rout);
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
            System.out.println(rq);
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
        HashMap<Integer, HashSet<Integer>> rin = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> rout = new HashMap<Integer, HashSet<Integer>>();
        for (Integer i : set) {
            HashSet<Integer> temp = new HashSet<Integer>();
            rin.put(i,temp);
            temp = new HashSet<Integer>();
            rout.put(i, temp);
        }
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

    public Queue sortHashMapByValuesReturnQ(Queue<Integer> qu, HashMap<Integer, Integer> aMap) {
       List mapKeys = new ArrayList(qu);
       List mapValues = new ArrayList(aMap.values());
       Collections.sort(mapValues);
       Collections.sort(mapKeys);

       Queue<Integer> q = new LinkedList<Integer>();

       Iterator valueIt = mapValues.iterator();
       while (valueIt.hasNext()) {
           Integer val = (Integer) valueIt.next();
           Iterator keyIt = mapKeys.iterator();

           while (keyIt.hasNext()) {
               Object key = keyIt.next();
               Integer comp1 = aMap.get(key);
               Integer comp2 = val;
               if (comp1 != null && comp2 != null && comp1.equals(comp2)) {
                   aMap.remove(key);
                   mapKeys.remove(key);
                   q.add((Integer) key);
                   break;
               }
           }
       }
       return q;
    } 
  
    public static void main(String[] args) {
        try {
            MAS foo;
            PrintWriter writer = new PrintWriter("easylife.out");
            for (int i = 3; i <= 3; i++) {
                    foo = new MAS(i);
                    foo.setUp();
                    for (Integer k : foo.realanswer) {
                        writer.print(k + " ");
                    }
                    writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}