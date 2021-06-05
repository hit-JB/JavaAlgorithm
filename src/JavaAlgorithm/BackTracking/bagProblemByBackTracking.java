package JavaAlgorithm.BackTracking;

import java.util.Arrays;

public class bagProblemByBackTracking extends SpecialProblem{
    private int[] current;
    private int[] x;
    private final int[] w;
    private final int[] v;
    private final int c;
    private final int n;
    static int most=0;
    public static void main(String[] args){
        int[] w = {2,3,4,5};
        int[] v = {3,4,5,7};
        bagProblemByBackTracking exa = new bagProblemByBackTracking(new int[w.length],w,v,9);
        exa.getSol();
        exa.printSolution(1);
    }
    public void getSol(){
        explore(0);

    }
    public void explore(int k){
       if (isComplete(k)){
           int sum =0;
        for(int i =0;i<n;i++){
            if(x[i] == 1){
                sum += v[i];
            }
        }
        if(sum >= most){
            most = sum;
            current = x.clone();
        }
       }
       else{
           for(int j=0;j<2;j++)
           {
               int[] x_copy = x.clone();
               x[k] = j;
               if (isPartial(k)){
                   explore(k+1);
               }
               x = x_copy.clone();
           }
       }
    }
    public bagProblemByBackTracking(int[] X,int[] W,int[] V,int C){
        x=X;
        w=W;
        v=V;
        c=C;
        n = x.length;
    }

    @Override
    public boolean isPartial(int k) {
        int sum = 0;
        int addAll =0;
        for(int i=0;i<k+1;i++){
            if(x[i] == 1) {
                sum =sum + w[i];
                addAll += v[i];
            }
        }
        for(int i=k+1;i<n;i++)
            addAll += v[i];
        return sum <= c && addAll >= most;
    }

    @Override
    public boolean isComplete(int k) {
        return k>=w.length;
    }

    @Override
    public void printSolution(int k) {
        int sum=0;
        for(int i=0;i<current.length;i++){
            if(current[i] ==1) {
                System.out.println(" " + w[i]);
                sum +=v[i];
            }
        }
        System.out.println("The all value is: "+ sum);
    }
}
