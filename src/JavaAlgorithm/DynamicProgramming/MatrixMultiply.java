package JavaAlgorithm.DynamicProgramming;

public class MatrixMultiply {
    public static void main(String[] args){

        int [] p = {30,35,15,5,10,20,25};
        Pair matrix  = matrixOrder(p);
        printOptimalParens(matrix.getS(),0,p.length-2);

    }
    public static void printOptimalParens(int[][] s ,int i ,int j){
        if(i==j)
            System.out.printf("A"+i);
        else{
            System.out.print("(");
            printOptimalParens(s,i,s[i][j]);
            printOptimalParens(s,s[i][j]+1,j);
            System.out.print(")");
        }
    }
    public static Pair matrixOrder(int[] p){
        int n =p.length-1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        for(int i=0;i<n;i++){
            m[i][i] = 0;
        }
        for(int j=0;j<n;j++)
            for(int i=0;i<n;i++) {
                if(i==j)
                    continue;
                m[i][j] = (int) (Double.POSITIVE_INFINITY);
            }
        for (int j=0;j<n;j++){
            for(int i=j-1;i>=0;i--)
            {
                int q = (int)(Double.POSITIVE_INFINITY);
                int min;
                for(int k=i; k<j; k++){
                    min = m[i][k]+m[k+1][j]+p[i]*p[k+1]*p[j+1];
                    if(min < q)
                    {
                        q = min;
                        s[i][j] = k;
                    }

                }
                m[i][j] = q;
            }
        }
        return new Pair(m,s);
    }

}
class Pair{
    private int[][] m;
    private int[][] s;
    Pair(int[][] m,int[][] s){
        this.m = m;
        this.s = s;
    }
    public int[][] getM(){
        return m;
    }
    public int[][] getS(){
        return s;
    }
}