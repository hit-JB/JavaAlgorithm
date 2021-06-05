package JavaAlgorithm.BackTracking;

import com.sun.source.tree.ArrayAccessTree;

import java.util.Arrays;

public class subsetSum {
    public static void main(String[] args){
        int[] set = {1,2,3};
        int[] x = new int[set.length];
        boolean[][] answer = isSol(set);
        for(boolean[] e:answer) {
            for (boolean e1 : e)
                System.out.print(" "+e1);
            System.out.println();
        }

//        boolean flag = getSol(set,x,0,5);
//        System.out.println("flag: "+flag);
//        for(int i=0;i<x.length;i++){
//            if(x[i] ==1)
//                System.out.print(" " + set[i]);
//        }
    }
    public static boolean getSol(int[] set,int[] x,int k,int c){
        int sum = 0;
        boolean flag = false;
        for(int i=0;i<k;i++){
            if(x[i] == 1)
                sum += set[i];
        }
        if( k == set.length && sum !=c)
            return false;
        if(sum == c)
            return true;
        else
        {
            if(sum < c)
            {
                x[k] =0;
                flag = getSol(set,x,k+1,c);
            }
            if(flag)
                return true;
            else {
                if (sum + set[k] <= c) {
                    x[k] = 1;
                    flag = getSol(set, x, k + 1, c);
                }
                if(flag)
                    return true;
                else {
                    x[k]= 0;
                    return false;
                }
            }

        }
    }
    public static boolean[][] isSol(int[] set){
        int sum =0;
        for(int e:set)
            sum +=e;
        boolean[][] answer = new boolean[set.length+1][sum+1];
        for(int i=1;i<answer.length;i++)
            answer[i][0] = true;
        Arrays.fill(answer[0], false);
        answer[0][0] = true;
        for(int i=1;i<answer.length;i++){
            for(int j=1;j<answer[0].length;j++){
                answer[i][j] = answer[i-1][j] || (j-set[i-1] >= 0 && answer[i-1][j-set[i-1]]);
            }
        }
        return answer;
    }
}
