package JavaAlgorithm.DynamicProgramming;
import java.util.*;

public class TheCiceroneAbacus {

    public static void main(String[] args){
        int[] p = {2,3,4,5};
        int[] k = {3,4,5,7};
        int[] c = {8,10,11,13};
        int[][][] map = getSol(p,k,c);
        for(int[] i:map[3]) {
            for (int j : i)
                System.out.print(" "+j);
            System.out.println();
        }
    }
    public static int[][][] getSol(int[] p,int [] k,int[] c){
        int[][][] map = new int[c.length][][];
        int numOfPerson = c.length;
        for(int i =0;i<numOfPerson;i++){
            Map<String,int[]> pk = Extend(p,k,c[i]);
            map[i] = Knapsack.getSol(pk.get("newp"),pk.get("newk"),c[i]);
        }
        return map;
    }
    public static Map<String,int[]> Extend(int[] p, int[] k, int c){
        List<Integer> newp = new ArrayList<>();
        List<Integer> newk = new ArrayList<>();
        for(int i =0;i<p.length;i++){
            int num = (int) (-1+Math.sqrt(1+8.*c/p[i])) / 2;
            for(int j =0; j<num; j++){
                newp.add(p[i] * (j+1));
                newk.add(k[i] * (j+1));
            }
        }
       Map<String,int[]> pk= new HashMap<>();
        int[] a = new int[newp.size()];
        int[] b = new int[newk.size()];
        for(int i=0;i<a.length;i++){
            a[i] = newp.get(i);
            b[i] = newk.get(i);
        }
        pk.put("newp",a);
        pk.put("newk",b);
        return pk;
    }
}
