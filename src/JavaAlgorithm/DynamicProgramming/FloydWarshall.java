package JavaAlgorithm.DynamicProgramming;

public class FloydWarshall {
    private static int MAX_VALUE = 1000;
    public static void main(String[] args){
        int[][] initialMap = {{0,3,8,MAX_VALUE,-4},{MAX_VALUE,0,MAX_VALUE,1,7},
                {MAX_VALUE,4,0,MAX_VALUE,MAX_VALUE},{2,MAX_VALUE,-5,0,MAX_VALUE},{MAX_VALUE,MAX_VALUE,
                MAX_VALUE,6,0}};
        int[][] dist = getSol(initialMap);
        for(int[] e:dist) {
            for (int ele : e)
                System.out.print(" " + ele);
            System.out.println();
        }
    }
    public static int[][] getSol(int[][] initialMap){
        int [][] map = new int[initialMap.length][initialMap.length];
        int [][] path = new int[map.length][map.length];
        for(int i = 0;i<map.length;i++){
            for(int j = 0;j<map.length;j++){
                map[i][j] = initialMap[i][j];
                if(map[i][j]<MAX_VALUE && i!=j){
                    path[i][j] = i;
                }
            }
        }
        int num = map.length;
        for(int k=0;k<num;k++)
            for(int i =0;i<num;i++){
                for(int j = 0;j<num;j++){
                   {
                        if(map[i][k]+map[k][j]<map[i][j]){
                            map[i][j] = map[i][k]+map[k][j];
                            path[i][j] = path[k][j];
                        }

                    }
                }
            }
        for(int[] e:path) {
            for (int ele : e)
                System.out.print(" " + ele);
            System.out.println();
        }
        System.out.println();
        return map;
    }
}
