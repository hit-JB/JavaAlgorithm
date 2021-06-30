package JavaAlgorithm.DynamicProgramming;


import java.util.*;

public class Dijkstra {
    static final int MAX_VALUE=1000;
    public static void main(String[] args){
        int[][] map = {{0,10,MAX_VALUE,MAX_VALUE,5},
                {MAX_VALUE,0,1,MAX_VALUE,2},
                {MAX_VALUE,MAX_VALUE,0,4,MAX_VALUE},
                {7,MAX_VALUE,6,0,MAX_VALUE},
                {MAX_VALUE,3,9,2,0}};

        Map<Integer,Integer> a = new HashMap<>();

        DijkstraAlgorithm(map);
    }
    public static void DijkstraAlgorithm(int[][] map)
    {
        Map<Integer,Integer> s = new HashMap<>();
        Map<Integer,Integer> u = new HashMap<>();
        for(int i=0;i<map[0].length;i++) {
           u.put(i,map[0][i]);
        }

        while(!u.isEmpty())
        {
            Set<Map.Entry<Integer,Integer>> a = u.entrySet();
            List<Map.Entry<Integer,Integer>> sort = new ArrayList<>(a);
            sort.sort(Comparator.comparingInt(Map.Entry::getValue));
            Map.Entry<Integer,Integer> e = sort.get(0);
            u.remove(e.getKey());
            s.put(e.getKey(),e.getValue());
            for(Map.Entry<Integer,Integer> adj:u.entrySet())
            {
                if(map[e.getKey()][adj.getKey()]!=MAX_VALUE)
                {
                    u.put( adj.getKey(),Math.min(adj.getValue(),e.getValue()+map[e.getKey()][adj.getKey()]));
                }
            }
        }
        System.out.println(s);
    }
}
