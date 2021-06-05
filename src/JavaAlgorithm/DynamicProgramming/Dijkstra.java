package JavaAlgorithm.DynamicProgramming;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra {
    static final int MAX_VALUE=1000;
    public static void main(String[] args){
        int[][] map = new int[7][7];
        MulEle<Integer[],Integer[]> a = new MulEle<>(new Integer[16],new Integer[15]);
    }
    public static Map<Integer,Integer> getSol(int[][] map, int home, int school){
        int[] dist = new int[map.length];
        for(int i = 0;i<dist.length;i++)
        {
            if(i == home)
                dist[i] = 0;
            else
                dist[i] = MAX_VALUE;
        }
        for(int i =0;i<map.length;i++){
            for(int k = 0; k<map.length ;k++){
            }
        }
        return null;
    }
    public static MulEle<Integer,Integer> min(int... a){
        int min = MAX_VALUE;
        int id = 0;
        for(int i =0;i<a.length;i++){
            if(a[i] < min){
                min = a[i];
                id = i;
            }
        }
        return  new MulEle<>(min,id);
    }
}
