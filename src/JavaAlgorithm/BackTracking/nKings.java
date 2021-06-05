package JavaAlgorithm.BackTracking;

import java.util.ArrayList;
import java.util.List;
import java.math.*;
public class nKings {
    public static void main(String[] args) {
       List<Integer> place = new ArrayList<>();
       int length = 4;
       Boolean i= getSol(place,length);
       System.out.println(i);
       int[][] locationMap = new int[length][length];
      for(int k =0;k<locationMap.length;k++)
          locationMap[k][place.get(k)] =1;
      for(int k=0;k<locationMap.length;k++)
      {
          for(int j =0;j<locationMap.length;j++)
              System.out.print(" "+locationMap[k][j]);
          System.out.println();
      }
    }

    public static Boolean getSol(List<Integer> place, Integer length) {
        if (place.size() == length) {
            return true;
        } else {
            Integer lastIndex = place.size();
            Boolean flag;
            int i=0;
            while(i<length){
                int x=0;
                while(x<place.size())
                 {
                    if (i == place.get(x) || Math.abs(lastIndex-x) == Math.abs(i-place.get(x)))
                    {
                        break;
                    }
                    x++;
                }
                if(x == place.size()){
                    place.add(i);
                    flag =  getSol(place,length);
                    if(!flag)
                        place.remove(place.size()-1);
                    else
                        return true;
                }
                i++;
            }
           return false;
        }
    }
}
