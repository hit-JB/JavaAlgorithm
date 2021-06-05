package JavaAlgorithm.DynamicProgramming;

import com.company.Interfacetest;

import java.util.ArrayList;
import java.util.List;

public class MstPrim {
    static final int MAX_VALUE = 1000;
    public static void main(String[] args){
        int[][] map = {{0,1,MAX_VALUE,4},{1,0,2,MAX_VALUE},{MAX_VALUE,2,0,3},{4,MAX_VALUE,3,0}};
        MulEle<Integer,int[][]> min_dist= getSol(map,0);
        System.out.println("min dist: "+min_dist.getE1());
        for(int[] p:min_dist.getE2()){
            for(int e:p){
                System.out.print(" "+ e);
            }
            System.out.println();
        }
    }
    public static MulEle<Integer,int[][]> getSol(int[][] map,int initial_index){
        int all_weight = 0;
        int[][] connect_map = new int[map.length][map.length];
        List<Integer> starPoints = new ArrayList<>();
        List<Integer> endPoints = new ArrayList<>();
        for(Integer i=0;i<map.length;i++)
            endPoints.add(i);
        Integer index = endPoints.get(initial_index);
        while(endPoints.size() > 1){
            starPoints.add(index);
            endPoints.remove(index);
            int min = MAX_VALUE;
            Integer min_index = endPoints.get(0);
            Integer min_start = endPoints.get(0);
            for(Integer startPoint : starPoints) {
                for (Integer endPoint : endPoints) {
                    if (map[startPoint][endPoint] < min) {
                        min = map[startPoint][endPoint];
                        min_index = endPoint;
                        min_start = startPoint;
                    }
                }
            }
            connect_map[min_start][min_index] = 1;
            all_weight += min;
            index = min_index;
        }
        return new MulEle<>(all_weight,connect_map);
    }
    public static Integer getRadarLocation(int[][] connectMap,int s,int distMap){
        return null;
    }
}
