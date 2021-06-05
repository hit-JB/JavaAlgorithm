package JavaAlgorithm.DynamicProgramming;

public class HumanGeneFunctions {
    public static void main(String[] args){
        int[][] m = {{5,-1,-2,-1,-3},{-1,5,-3,-2,-4},{-2,-3,5,-2,-2},{-1,-2,-2,5,-1},{-3,-4,-2,-1,1000}};
        char[] c = {'A','C','G','T','-'};
        String  a= "AGTGATG";
        String  b = "GTTAG";
        int[][] map = matchTheGens(a,b,m);
        for(int[] i:map) {
            for (int j : i)
                System.out.print(" "+j);
            System.out.println();
        }
    }
    public static int[][] matchTheGens(String a, String b, int[][] m) {

        int[][] map = new int[a.length() + 1][b.length() + 1];
        map[0][0] = 0;
        for(int i = 0  ;i<map.length;i++){
            for(int j = 0;j<map[0].length;j++)
            {
                if(i==0&&j==0)continue;
                else
                    if(i>0 && j==0){
                        map[i][0] = map[i-1][0] + matchWeight(m,a.charAt(i-1),'-');
                     }
                    else
                         if (i==0 && j>0){
                             map[0][j] = map[0][j-1] + matchWeight(m,'-',b.charAt(j-1));
                         }
                         else
                             map[i][j] = Math.max(Math.max(map[i-1][j-1]+matchWeight(m,a.charAt(i-1),b.charAt(j-1)),map[i-1][j]+matchWeight(m,'-',b.charAt(j-1)))
                                     ,map[i][j-1]+matchWeight(m,a.charAt(i-1),'-'));
            }
        }
        return map;
    }
    public static int matchWeight(int[][] m,char c1,char c2){
        getCharNum charNum = c -> {
            String C = String.valueOf(c);
            switch (C){
                case "A":return 0;
                case "C":return 1;
                case "G":return 2;
                case "T":return 3;
                default:return  4;
            }
        };
        return m[charNum.get(c1)][charNum.get(c2)];
    }
}
interface getCharNum{
    abstract int get(char c);
}
