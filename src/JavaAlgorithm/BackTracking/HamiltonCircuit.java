package JavaAlgorithm.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class HamiltonCircuit {
    public static void main(String[] args){
        int[][] map ={{0,1,1,1,0},{1,0,1,0,1},{1,1,0,1,0},{1,0,1,0,1},{0,1,0,1,0}};
        List<Integer> path = new ArrayList<>();
        path.add(4);
        Boolean flag = findPath(path,map);
        System.out.println(flag);
        for(int i=0;i<path.size();i++)
            System.out.print((path.get(i)+1)+" -> ");

    }
    public static Boolean findPath(List<Integer> path,int[][] map){
        if(path.size() == map.length) {
            if(map[path.get(path.size()-1)][path.get(0)] ==1)
                return true;
            else
                return false;
        }
        else{
            int lastIndex = path.get(path.size()-1);
            int direction = 0;
            boolean flag;
            while(direction<map.length){
                int point =0;
                while(point<path.size()-1){
                    if(direction == path.get(point)){
                        break;
                    }
                    point++;
                }
                if(point == path.size()-1 && map[lastIndex][direction] == 1){
                    path.add(direction);
                    flag = findPath(path,map);
                    if(!flag)
                        path.remove(path.size()-1);
                    else
                        return true;
                }
                direction++;
            }
            return false;
        }
    }
}
