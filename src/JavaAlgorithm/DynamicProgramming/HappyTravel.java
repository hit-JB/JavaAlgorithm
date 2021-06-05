package JavaAlgorithm.DynamicProgramming;

public class HappyTravel {
    public static void main(String[] args) {
        int[] bag = {1,2,3,4,5,6};
        int[] x = getSol(bag);
        for(int e:x)
            System.out.print(" "+e);
    }
    public static int[] getSol(int[] p){
        int i = p.length;
        int sum=0;
        for(int e:p){
            sum +=e;
        }
        int[][] map = Knapsack.getSol(p,p,(int) sum/2);
        int [] x = Knapsack.drawMap(map,p.length,(int) sum/2,p,p);
        return x;
    }

}
