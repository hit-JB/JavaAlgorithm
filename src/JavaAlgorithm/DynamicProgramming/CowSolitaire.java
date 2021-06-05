package JavaAlgorithm.DynamicProgramming;

public class CowSolitaire {
    public static void main(String[] args){
        int[][] map = {{8,1,3,1},{8,4,12,12},{5,9,13,7},{10,12,1,2}};
        Pair pathValue = optimalPath(map);
        int[][] m = pathValue.getM();
        for(int i=0;i<m.length;i++) {
            for (int j = 0; j < m.length; j++)
                System.out.print(" " + m[i][j]);
            System.out.println();
        }
        System.out.println();
        drawPath(pathValue.getS());

    }
    public static Pair optimalPath(int[][] map){
        int n = map.length;
        int[][] m = new int[n+1][n+1];
        int[][] s = new int[n+1][n+1];
        for(int i = 0;i<m.length;i++)
            m[i][0] =0;
        for(int j=0;j<m.length;j++)
            m[n][j]=0;
        for(int i =n-1;i>=0;i--)
            for(int j = 1;j<m.length;j++){
                if(m[i+1][j]>m[i][j-1]) {
                    m[i][j] = m[i + 1][j] + map[i][j-1];
                    s[i][j] = 1;
                }
                else {
                    m[i][j] = m[i][j - 1] + map[i][j-1];
                    s[i][j] = -1;
                }
            }
        return new Pair(m,s);
    }
    public static void drawPath(int[][] s){
        int[][] map = new int[s.length][s.length];
        for(int i =0;i<map.length;i++) {
            for (int j = 0; j < map.length; j++)
                map[i][j] = 0;
        }
        int i = 0;
        int j = map.length-1;
        while(true){
            if(s[i][j] == 1){
                map[i][j] = 1;
                i = i+1;
            }
            else{
                map[i][j] = 1;
                j = j-1;
            }
            if(i== map.length-2 && j==1) {
                map[i][j] = 1;
                break;
            }
        }
        for(i=0;i<map.length;i++) {
            for ( j = 0; j < map.length; j++)
                System.out.print(" " + map[i][j]);
            System.out.println();
        }

    }
}
