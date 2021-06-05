package JavaAlgorithm.Graph;

import JavaAlgorithm.BackTracking.*;

import java.util.*;

public class DFS {
    static final int WHITE = 0;
    static final int GRAY =1;
    static final int BLACK =2;
    private final int[][] map;
    private int[] color;
    private Integer[] parent;
    private List<Integer> queue;
    private final int[][] map_t;

    boolean flag;
    int[] startTime,finishTime;
    public static void main(String[] args){
        int[][] map = {{0,1,0,0,0,0,0,0},
                {0,0,1,0,1,1,0,0},
                {0,0,0,1,0,0,1,0},
                {0,0,1,0,0,0,0,1},
                {1,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,1,0},
                {0,0,0,0,0,1,0,1},
                {0,0,0,0,0,0,0,1}};
        int[][] map_T = new int[map[0].length][map.length];
        for(int i=0;i<map.length;i++) {
            for (int j = 0; j < map[0].length; j++)
                map_T[j][i] = map[i][j];}


        DFS exa = new DFS(map,map_T);
        exa.constructDfs();
        exa.printSol();
        int[] finishOrder = exa.sort();
        Map<Integer,List<Integer>> order = exa.constructOrder(finishOrder);

        for(Map.Entry<Integer,List<Integer>> entry:order.entrySet()){
            System.out.print("Vertex: "+ entry.getKey()+" ");
            for(Integer e:entry.getValue())
                System.out.print(" "+e);
            System.out.println();
        }
    }
    public  int[] sort(){
        int[] index_order = new int[finishTime.length];
        for(int i=0;i<index_order.length;i++)
            index_order[i] = i;
        for(int i=0;i< finishTime.length;i++){
            if(i+1< finishTime.length && finishTime[i+1]>finishTime[i]){
                int dist = finishTime[i];
                finishTime[i] = finishTime[i+1];
                finishTime[i+1] = dist;
                int index = index_order[i+1];
                index_order[i+1] = index_order[i];
                index_order[i] = index;
            }
        }
        return index_order;
    }
    public DFS(int[][] map,int[][] map_T) {
        this.map = new int[map.length][map[0].length];
        GridPath.fillMatrix(map,this.map);
        color = new int[map.length];
        Arrays.fill(color,WHITE);
        queue = new ArrayList<>();
        parent = new Integer[map.length];
        flag = false;
        startTime = new int[map.length];
        finishTime = new int[map.length];
        this.map_t = map_T;
    }
    public void constructDfs(){
        int time = 0;
        boolean colorFlag = false;
        for(int i=0;i<map.length;i++){
            if(color[i] == WHITE){
                queue.add(i);
                startTime[i] = time;
                while(!queue.isEmpty()){
                    Integer node = queue.get(queue.size()-1);
                    int j=0;
                    time +=1;
                    while (j< map.length){
                        if(color[j] == WHITE && map[node][j] == 1)
                        {
                            //startTime[j] = time;
                            color[j] = GRAY;
                            queue.add(j);
                            break;
                        }
                        if(color[j] == GRAY &&  map[node][j] ==1)
                        {
                            flag = true;
                        }
                        j++;
                    }
                    if(j==map.length) {
                        finishTime[node] = time;
                        color[node] = BLACK;
                        queue.remove(queue.size()-1);
                    }
                }
            }
        }
    }
    public Map<Integer,List<Integer>> constructOrder(int[] order){
        int time = 0;
        boolean colorFlag = false;
        Map<Integer,List<Integer>> component = new HashMap<>();
        Arrays.fill(color,WHITE);
        for(int i:order){

            if(color[i] == WHITE){
                component.put(i,new ArrayList<Integer>());
                Integer max_node = i;
                queue.add(i);
                while(!queue.isEmpty()){
                    Integer node = queue.get(queue.size()-1);
                    int j=0;
                    while (j< map.length){
                        if(color[j] == WHITE && map_t[node][j] == 1)
                        {
                            component.get(max_node).add(j);
                            color[j] = GRAY;
                            queue.add(j);
                            break;
                        }
                        j++;
                    }
                    if(j==map.length) {
                        color[node] = BLACK;
                        queue.remove(queue.size()-1);
                    }
                }
            }
        }
        return component;
    }
    public void printSol(){
        for(int i=0;i<map.length;i++){
            System.out.println("  Node: "+i+" start time: "+startTime[i]+" finish time: "+finishTime[i]);
        }
    }
}
