package JavaAlgorithm.BackTracking;

public class CalculateRoute extends SpecialProblem {
    static final int BLOCK = 1;
    private final int[][] map ;
    private int[] x ;
    private int[] path;
    private static double maximum;
    private final int startIndex;
    private final int endIndex;
    private final double[] weight;
    public static void main(String[] args){
        int[][] map = {{0,1,1,0,0,0},{0,0,1,1,0,0},{0,0,0,1,1,0},
                {0,0,1,0,1,1},{0,0,0,0,0,1},{0,0,0,0,0,0}};
        CalculateRoute exa = new CalculateRoute(map,0,5);
        exa.explore(1);
        exa.printSolution(1);
    }
    public CalculateRoute(int[][] map, int startIndex, int endIndex) {
        this.map = map;
        x = new int[map.length];
        x[0] = startIndex;
        path = new int[map.length];
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        maximum = 0;
        weight = new double[]{1, 0.2, 0.1, 0.6, 0.3, 1};
    }
    public void explore(int k){
        if(isComplete(k) || k ==x.length){
            if(!isComplete(k))
                return;
            double sum = 1;
            for(int i=0;i<k;i++)
                sum *= weight[i];
            if(sum > maximum){
                maximum = sum;
                GridPath.fillArray(x,path);
            }
        }
        else{
            int[] x_copy = new int[x.length];
            GridPath.fillArray(x,x_copy);
            for(int i=0;i<x.length;i++){
                x[k] = i;
                if(isPartial(k)){
                    explore(k+1);
                    GridPath.fillArray(x_copy,x);
                }
            }
        }
    }
    @Override
    public boolean isPartial(int k) {
        int nextPoint = x[k];
        int prePoint = x[k-1];
        for(int i = 0;i<k;i++) {
            if (x[i] == nextPoint)
                return false;
        }
        if (map[prePoint][nextPoint] == 0)
            return false;
        return true;
    }

    @Override
    public boolean isComplete(int k) {
        if( k == x.length && x[k-1] != endIndex){
            return false;
        }
        else {
            return x[k - 1] == endIndex;
        }
    }

    @Override
    public void printSolution(int k) {
        for(int i = 0;i<path.length;i++)
            System.out.print(path[i]+1+"->");
        System.out.println();
        System.out.println("maximum: "+maximum);
    }
}
