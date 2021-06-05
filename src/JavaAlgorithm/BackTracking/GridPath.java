package JavaAlgorithm.BackTracking;

import java.awt.*;

public class GridPath extends SpecialProblem {
    private int[] x;
    private int[] current;
    private int minimum  = 10000;
    private final int factor;
    private final int[][] map;
    static int count = 0;
    static int[][] location;
    public static void main(String[] args){
        int[][] map = {{1,2},{3,4}};
        GridPath exa = new GridPath(map,11,4);

        exa.explore(0);
        exa.printSolution(1);
    }
    public GridPath(int[][] map,int k,int steps) {
        factor = k;
        this.map = map;
        x = new int[steps];
        current = new int[steps];
        location= new int[steps][2];
    }
    public static void fillArray(int[] a, int[] b){
        for(int i=0;i<a.length;i++)
            b[i] = a[i];
    }
    public static void fillMatrix(int[][] a,int[][] b){
        for(int i=0;i<a.length;i++)
            for(int j=0;j<a[0].length;j++)
                b[i][j] = a[i][j];
    }
    public void explore(int k) {
        if (isComplete(k)) {
            count += 1;
        }
        else {
            if(k==x.length)
                return;
            int[][] currentLocation = new int[x.length][2];
            int[] currentX = new int[x.length];
            fillMatrix(location, currentLocation);
            fillArray(x, currentX);
            if (k == 0) {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {

                        x[k] = map[i][j];
                        fillArray(location[k], new int[]{i, j});
                        explore(k + 1);
                        fillArray(currentX, x);
                        fillMatrix(currentLocation, location);

                    }
                }
            } else {
                int index_i = currentLocation[k - 1][0];
                int index_j = currentLocation[k - 1][1];
                if (index_i - 1 >= 0) {
                    x[k] = map[index_i - 1][index_j];
                    fillArray(new int[]{index_i - 1, index_j},location[k]);
                    explore(k + 1);
                    fillArray(currentX, x);
                    fillMatrix(currentLocation, location);
                }
                if (index_i+1 < map.length) {
                    x[k] = map[index_i + 1][index_j];
                    fillArray( new int[]{index_i + 1, index_j},location[k]);
                    explore(k + 1);
                    fillArray(currentX, x);
                    fillMatrix(currentLocation, location);
                }
                if (index_j - 1 >= 0) {
                    x[k] = map[index_i][index_j - 1];
                    fillArray( new int[]{index_i, index_j - 1},location[k]);
                    explore(k + 1);
                    fillArray(currentX, x);
                    fillMatrix(currentLocation, location);
                }
                if (index_j + 1 < map[0].length) {
                    x[k] = map[index_i][index_j + 1];
                    fillArray( new int[]{index_i, index_j + 1},location[k]);
                    explore(k + 1);
                    fillArray(currentX, x);
                    fillMatrix(currentLocation, location);
                }
            }
        }
    }


    @Override
    public boolean isPartial(int k) {
        return true;
    }

    @Override
    public boolean isComplete(int k) {
        if(k < x.length)
            return false;
        else{
            int sum = 0;
            int startIndex = x.length-1;
            for(int i =startIndex;i>=0;i--){
                sum += Math.pow(10,(startIndex-i)) * x[i];
            }
            if(sum % factor == 0) {
                if(sum < minimum)
                {
                    minimum = sum;
                    fillArray(x,current);
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public void printSolution(int k) {
        System.out.print("count "+count+" minimum: "+minimum);
    }
}
