package JavaAlgorithm.DynamicProgramming;

public class CommonLongestSubsequence {
    public static void main(String[] args){
        String a = new String("abcdab");
        String b = new String("bdcaba");
        String c = makeLcs(a,b);
        int[][] comMatrix = makeLcsThroughDynamic(a,b);
        for(int i = 0;i<comMatrix.length;i++){
            for(int j=0;j<comMatrix[0].length;j++){
                System.out.print(" "+comMatrix[i][j]);
            }
            System.out.println();
        }

    }
    public static String makeLcs(String a,String b){
        if(a.length()==0 || b.length() ==0)
            return "";
        char aEnd = a.charAt(a.length()-1);
        char bEnd = b.charAt(b.length()-1);
        if(aEnd == bEnd){
            return makeLcs(a.substring(0,a.length()-1),b.substring(0,b.length()-1)) + a.charAt(a.length() - 1);
        }
        else
        {
            String s1 = makeLcs(a,b.substring(0,b.length()-1));
            String s2 = makeLcs(a.substring(0,a.length()-1),b);
            return s1.length() > s2.length() ? s1:s2;
        }
    }
    public static int[][] makeLcsThroughDynamic(String a,String b){
        int[][] c = new int[a.length()+1][b.length()+1];
        for(int i =0 ;i<a.length();i++)
            c[i][0] = 0;
        for(int j = 0 ;j<b.length();j++)
            c[0][j] = 0;
        for(int i = 1;i<a.length()+1;i++)
        {
            for(int j = 1;j<b.length()+1;j++){
                if(a.charAt(i-1)==b.charAt(j-1))
                    c[i][j] = c[i-1][j-1] + 1;
                else{
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        return c;
    }
}
