package JavaAlgorithm.BackTracking;

public class FloorFlip extends SpecialProblem{
    private boolean[][] map;
    final boolean[][] initialMap ;
    private boolean[] x;
    private boolean[] current;
    private int minimum;
    private static int count = 0;
    public static void main(String[] args){
       boolean[][] map = {{true,true},{false,true}};
       FloorFlip exa = new FloorFlip(map);
       exa.explore(0);
       exa.printSolution(1);

    }
    public static void copyArray(boolean[] a, boolean[] b){
        for(int i=0;i<a.length;i++)
            b[i] = a[i];
    }
    public static  void copyMatrix(boolean[][] a,boolean[][] b){
        for(int i=0;i<a.length;i++)
            for(int j=0;j<a[0].length;j++)
                b[i][j] =a[i][j];
    }
    public FloorFlip(boolean[][] map) {
        this.initialMap = map;
        this.map = new boolean[map.length][map[0].length];
        copyMatrix(map,this.map);
        x = new boolean[map.length * map[0].length];
        current = new boolean[x.length];
        minimum = x.length;
    }
    public void explore(int k) {
        if(k == x.length) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++)
                    if (map[i][j])
                        return;
            }
            if (count < minimum) {
                minimum = count;
                copyArray(x,current);
            }
            return;
        }
        if (isComplete(k)) {
            if (count < minimum) {
                minimum = count;
                copyArray(x,current);
            }
        } else {
            boolean[] x_copy = new boolean[x.length];
            copyArray(x,x_copy);
            int count_copy = count;
            boolean[][] map_copy = new boolean[map.length][map[0].length];
            copyMatrix(map,map_copy);

            for (boolean e : new boolean[]{false, true}) {
                x[k] = e;

                if(isPartial(k)){
                    for(boolean m:x)
                        System.out.print(" "+m);

                    System.out.println();


                    for(boolean[] ex:map) {
                        for (boolean e1 : ex)
                            System.out.print(" " + e1);
                        System.out.println();
                    }


                    if(e){
                        count +=1;
                        int index_i = k/ map[0].length;
                        int index_j = k-index_i * map[0].length;
                        map[index_i][index_j] = !map[index_i][index_j];
                        if(index_i-1>=0)
                            map[index_i-1][index_j] = !map[index_i-1][index_j];
                        if(index_j-1>=0)
                            map[index_i][index_j-1] = !map[index_i][index_j-1];
                        if(index_i+1<map.length)
                            map[index_i+1][index_j] = !map[index_i+1][index_j];
                        if(index_j+1<map[0].length)
                            map[index_i][index_j+1] = !map[index_i][index_j+1];
                        }

                    for(boolean[] ex:map) {
                        for (boolean e1 : ex)
                            System.out.print(" " + e1);
                        System.out.println();
                    }
                    explore(k+1);
                    }

                count = count_copy;
                copyArray(x_copy,x);
                copyMatrix(map_copy,map);
                }

            }
    }
    @Override
    public boolean isPartial(int k) {
        int index_i = k/map[0].length;
        int index_j = k-index_i * map[0].length;
        if (x[k] && (index_i - 1 >= 0 && !map[index_i - 1][index_j]))
            return false;
        else {
            if (!x[k] && (index_i - 1 >= 0 && map[index_i - 1][index_j]))
                return false;
        }
        if(index_i == map.length-1){
            if(x[k] && (index_j-1>=0 && !map[index_i][index_j-1]))
                return false;
            if(!x[k] && (index_j-1>=0 && map[index_i][index_j-1]))
                return false;
        }
        return true;
    }

    @Override
    public boolean isComplete(int k) {
//        if( k==x.length )
//        {
//            for (boolean[] booleans : map) {
//                for (boolean b : booleans)
//                    if (b)
//                        return false;
//            }
//            return true;
//        }

        if (!x[k]) {
            for (boolean[] booleans : map)
                for (boolean b : booleans)
                    if (b)
                        return false;
        }
        else {
            int index_i = k / map[0].length;
            int index_j = k - index_i * map[0].length;
            for (int i = 0; i < map.length; i++)
                for (int j = 0; j < map[0].length; j++) {
                    if (i == index_i && (j == index_j || (index_j - 1 > 0 && j == index_j - 1) ||
                            (index_j + 1 < map[0].length && j == index_j + 1))) {
                        if (!map[i][j])
                            return false;
                    } else if (j == index_j && (index_i - 1 >=0 && i == index_i - 1 || index_i + 1 < map.length
                            && i == index_i + 1)) {
                        if (!map[i][j])
                            return false;
                    } else {
                        if (map[i][j])
                            return false;
                    }

                }
        }
        return true;
    }
    @Override
    public void printSolution(int k) {
        for(int i=0;i<map.length;i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (current[i * map.length + j])
                    System.out.print(" " + 1);
                else
                    System.out.print(" " + 0);
            }
            System.out.println();
        }
        System.out.println("minimum: "+minimum);

        for(boolean[] e:map){
            for(boolean e1:e)
                System.out.print(" " +e1);
            System.out.println();
        }
    }
}

