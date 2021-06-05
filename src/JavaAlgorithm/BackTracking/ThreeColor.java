package JavaAlgorithm.BackTracking;

import JavaAlgorithm.DynamicProgramming.MulEle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ThreeColor {
    static final int BLUE =1;
    static final int YELLOW =2;
    static final int RED = 3;
    static final int[] colors = {BLUE,YELLOW,RED};
    public static void main(String[] args){
       int[][] map = {{0,1,1,1},{1,0,1,1},{1,1,0,1},{1,1,1,0}};
       List<Integer> coloringPoints = new ArrayList<>();
       Boolean coloringMap = printSol(map,coloringPoints);
       System.out.println("Can coloring the map?  "+ coloringMap);
       for(int i=0;i<coloringPoints.size();i++){
           System.out.println("Point " + i + " color: "+ coloringPoints.get(i));
       }
    }
    public static Boolean printSol(int[][] initialMap,List<Integer> coloringPoints){
        if(coloringPoints.size() == initialMap.length)
            return true;
        else {
            Boolean flag = false;
            for (int i = 0; i < colors.length; i++) {
                if (isReasonable(initialMap, coloringPoints, colors[i])) {
                    coloringPoints.add(colors[i]);
                    flag = printSol(initialMap, coloringPoints);
                    if(!flag){
                        coloringPoints.remove(coloringPoints.size() -1);
                    }
                }
                if(flag)
                    return true;
            }
            return flag;
        }
    }
    public static MulEle<List<Integer>,Boolean> getSol(int[][] initialMap){
        List<Integer> coloringPoints = new ArrayList<>();
        while(coloringPoints.size() < initialMap.length){
            if(isReasonable(initialMap,coloringPoints,BLUE)){
                coloringPoints.add(BLUE);
            }
            else
                if(isReasonable(initialMap,coloringPoints,YELLOW))
                    coloringPoints.add(YELLOW);
                else
                    if(isReasonable(initialMap,coloringPoints,RED))
                        coloringPoints.add(RED);
                    else
                        if(coloringPoints.size() > 1) {
                            changeSuper(coloringPoints, initialMap);
                            if(coloringPoints.size() ==1 && coloringPoints.get(0) == YELLOW)
                            {
                                return new MulEle<>(coloringPoints,false);
                            }
                        }
                        else
                            return new MulEle<>(coloringPoints,false);
        }
        return new MulEle<>(coloringPoints,true);
    }
    public static void changeSuper(List<Integer> c,int[][] map){
        int lastIndex = c.size() - 1;
            if (c.get(lastIndex) == BLUE) {
                if (isReasonable(map, c, YELLOW))
                    c.set(lastIndex, YELLOW);
                else
                    if(isReasonable(map,c,RED))
                        c.set(lastIndex,RED);
                    else
                    {
                        c.remove(lastIndex);
                        changeSuper(c,map);
                    }
            } else if (c.get(lastIndex) == YELLOW) {
                if (isReasonable(map, c, RED))
                    c.set(lastIndex, RED);
                else {
                    c.remove(c.get(lastIndex));
                    changeSuper(c, map);
                }
            } else {
                c.remove(c.get(lastIndex));
                changeSuper(c, map);
            }
    }
    public static boolean isReasonable(int[][] map,List<Integer> coloringPoints,Integer color){
        if(coloringPoints.size() ==0)
            return true;
        Integer index = coloringPoints.size();
        for(int i=0;i<coloringPoints.size();i++){
            if(map[i][index] == 1 && coloringPoints.get(i).equals(color))
                return false;
        }
        return true;
    }
}

