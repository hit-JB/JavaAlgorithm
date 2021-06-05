package JavaAlgorithm.BackTracking;

public class BacktrackingHamilton extends SpecialProblem{
    private final int[][] map;
    private int[] x;
    private static int minimum;
    private static int[] path ;
    private static int current = 0;
    private final int n;
    static final int MAX = 1000;
    public static void main(String[] args){
        final int[][] map = {{0,1,MAX,4},{1,0,2,MAX},{MAX,2,0,3},{4,MAX,3,0}};
        BacktrackingHamilton exa = new BacktrackingHamilton(map);
        exa.explore(1);
        exa.printSolution(1);
    }
    public BacktrackingHamilton(int[][] map) {
        this.map = map;
        this.n = map.length;
        this.x = new int[n];
        path  = new int[n];
        minimum = MAX;
    }
    public void explore(int k){
        if(isComplete(k)){
            if(current + map[x[n-1]][x[0
                    ]] < minimum)
            {
                minimum = current + map[x[n-1]][x[0]];
                path = x.clone();
            }
        }
        else{
            int[] x_copy = x.clone();
            int current_copy = current;
            for(int i=0;i<n;i++)
            {
                x[k] = i;
                current += map[x[k-1]][x[k]];
                if(isPartial(k))
                    explore(k + 1);
                x = x_copy.clone();
                current = current_copy;
            }
        }
    }
    @Override
    public boolean isPartial(int k) {
        for(int i=0;i<k;i++){
            if(x[k] == x[i])
                return false;
        }
        if(current + map[x[k-1]][x[k]] >=minimum)
            return false;
        if(k==n-1){
            return map[x[k]][x[0]] < MAX;
        }
        return true;
    }

    @Override
    public boolean isComplete(int k) {
        return k>=n;
    }

    @Override
    public void printSolution(int k) {
        int dist = 0;
        for(int i=0;i<n;i++){
            System.out.print(path[i] + " -> ");
        }
    }
}
