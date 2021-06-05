package JavaAlgorithm.DynamicProgramming;

public class BronzeCowParty {
    public static int MAX_VALUE=1000;
    public static void main(String[] args) {
        int[][] map = {{0, 3, 2, 4}, {3, 0, 1, 2}, {2, 1, 0, 6},
                {4, 2, 6, 0}};
        int partyPos = 1;
        int[][] dist = FloydWarshall.getSol(map);
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            if (dist[partyPos][i] + dist[i][partyPos] > max)
                max = dist[partyPos][i] + dist[i][partyPos];
        }
        for (int[] e : dist) {
            for (int ele : e)
                System.out.print(" " + ele);
            System.out.println();
        }
        System.out.print("Max time cost " + max);
    }

}
