package JavaAlgorithm.Graph;

import JavaAlgorithm.BackTracking.GridPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BFS {
    static final int WHITE = 0;
    static final int GRAY =1;
    static final int BLACK =2;
    private final int[][] map;
    private int[] color;
    private Integer[] parent;
    List<Integer> queue;
    int[] dist;
    public static void main(String[] args){
        int[][] map = {{0,1,1,0},{1,0,1,1},{1,1,0,0},{0,1,0,0}};
        BFS exa = new BFS(map);
        exa.construct(0);
        exa.printSol();
    }
    public BFS(int[][] map) {
        this.map = new int[map.length][map[0].length];
        GridPath.fillMatrix(map,this.map);
        color = new int[map.length];
        Arrays.fill(color,WHITE);
        queue = new ArrayList<>();
        parent = new Integer[map.length];
        dist = new int[map.length];
    }
    public void construct(int initialNode){
        color[initialNode] = WHITE;
        dist[initialNode] = 0;
        parent[initialNode] = null;
        queue.add(initialNode);
        while(queue.size() >0){
            Integer node = queue.remove(0);;
            for(int i=0;i<map[0].length;i++) {
                if(i==node)
                    continue;
                if (map[node][i] == 1) {
                    if(color[i] == WHITE)
                    {
                        color[i] = GRAY;
                        parent[i] = node;
                        queue.add(i);
                        dist[i] = dist[node] + 1;
                    }
                }
            }
            color[node] = BLACK;
        }
    }
    public void printSol(){
        for(int i=0;i<map.length;i++){
            System.out.println("Node: "+i+" dist: "+dist[i]+" prent: "+parent[i]);
        }
    }
}
