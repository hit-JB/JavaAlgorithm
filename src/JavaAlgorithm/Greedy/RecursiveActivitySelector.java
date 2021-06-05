package JavaAlgorithm.Greedy;
import JavaAlgorithm.DynamicProgramming.*;
class RecursiveActivitySelector {
    public static void main(String[] args){
        int[] s ={0,1,3,0,5,3,5,6,8,8,2,12,Integer.MAX_VALUE};
        int[] f ={0,4,5,6,7,8,9,10,11,12,13,14,Integer.MAX_VALUE};
        int[][] timePair = new int[s.length][2];
        int[] x= new int[s.length];
        for(int i=0;i<s.length;i++)
        {
            timePair[i][0] = s[i];
            timePair[i][1] = f[i];
            x[i] = 0;
        }

        int num = getSol(timePair,0,timePair.length-1, x);
        System.out.println("num of activity "+ num);
        for(int e :x)
            System.out.print(" " + e);
    }
    static int getSol(int[][] s,int i, int j,int[] x){//assume the s is sorted by the finish time
        int fistFinal = -1;
        for(int m=i+1; m<j; m++)
        {
            if (s[m][0] > s[i][1] && s[m][1] < s[j][0]) {
                fistFinal = m;
                x[fistFinal] = 1;
                break;
            }
        }
       if(fistFinal == -1)
           return 0;
       else{
           return  getSol(s,fistFinal,j,x) + 1;
       }
    }
}
