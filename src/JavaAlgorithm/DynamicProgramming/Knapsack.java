package JavaAlgorithm.DynamicProgramming;

public class Knapsack {
    public static void main(String[] args){
        int[] w = {2,3,4,5};
        int[] v = {3,4,5,7};
        int c = 9;
        int[][] map = getSol(w,v,c);
        for(int[] i:map) {
            for (int j : i)
                System.out.print(" "+j);
            System.out.println();
        }
        for(int e:drawMap(map,w.length,c,w,v)){
            System.out.print(" "+ e);
        }

    }
    public static int[][] getSol(int[] w,int[] v,int c){
        int[][] map = new int[w.length+1][c+1];
        for(int i = 0;i<map.length;i++)
            map[i][0] = 0;
        for(int j = 0;j<map[0].length;j++)
            map[0][j] = 0;
        for(int i =1;i<map.length;i++){
            for(int j = 1;j<map[0].length;j++){
                if(w[i-1] > j)
                    map[i][j] = map[i-1][j];
                else
                    if(w[i-1]<=j){
                        map[i][j] = Math.max(map[i-1][j],map[i-1][j-w[i-1]]+v[i-1]);
                    }
            }
        }
        return map;
    }
    public static int[] drawMap(int[][] map,int i ,int j,int[] w ,int [] v){
        int[] x = new int[i];
        int m =i;
        int n =j;
        while( m >0 && n> 0) {

            if (map[m][n] == map[m - 1][n]) {
                x[m - 1] = 0;
                m = m-1;
            } else {
                x[m - 1] = 1;
                n = n-v[m-1];
                m = m-1;
            }
        }

        return x;
    }
}
