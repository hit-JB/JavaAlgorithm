package JavaAlgorithm.ComputeGeometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Jarvis {
    private List<Point> points;

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        double[][] p = {{2., 1.}, {4., 1.}, {5., 2.}, {3., 3.}, {1., 2.}, {2., 2.}, {3., 2.}, {2., 1.5}};
        int index = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i][1] <= p[index][1]) {
                if (p[i][1] < p[index][1] || p[i][1] == p[index][1] && p[i][0] < p[index][0])
                    index = i;
            }
            points.add(new Point(p[i]));
        }
        points.sort((o1, o2) -> {
            if (o1.getY() > o2.getY() || o1.getY() == o2.getY() && o1.getX() > o2.getX())
                return 1;
            return -1;
        });
        Jarvis exa = new Jarvis(points);
        exa.construct();
    }

    public Jarvis(List<Point> points){
        this.points = points;
    }
    public void construct(){
        Point p0 = points.get(0);
        Point pm = points.get(points.size() -1);
        System.out.println();
        Stack<Point> s = new Stack<>();
        s.push(p0);
        points.remove(0);
        boolean flag = false;
       do {
           Point top = s.peek();
           if(!flag){
           points.sort((o1,o2)->{
               double cos1 = o1.subtract(top).getX() / o1.subtract(top).norm();
               double cos2 = o2.subtract(top).getX() / o2.subtract(top).norm();
               if(cos1 > cos2 || (cos1 == cos2 && o1.subtract(top).norm() > o2.subtract(top).norm()))
                   return -1;
               return 1;
           });
           }
           else {
               points.sort((o1,o2)->{
                   double cos1 = o1.subtract(top).getX() / o1.subtract(top).norm();

                   double cos2 = o2.subtract(top).getX() / o2.subtract(top).norm();
                   if(cos1 > cos2 || (cos1 == cos2 && o2.subtract(top).norm() > o1.subtract(top).norm()))
                   return 1;
                   return -1;
               });
           }

           double x = points.get(0).getX();
           double y = points.get(0).getY();
           if(points.get(0).compareTo(pm)==1) {
               points.add(p0);
               flag = true;
           }
           s.push(points.get(0));
           points.remove(0);
       } while(s.peek().compareTo(p0)!=1);
       for(Point e:s)
           System.out.println(e.getX()+" "+e.getY());
    }
}
