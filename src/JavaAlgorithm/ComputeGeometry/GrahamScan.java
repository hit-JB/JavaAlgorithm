package JavaAlgorithm.ComputeGeometry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
public class GrahamScan {
    private List<Point> points ;
    public static void main(String[] args){
        List<Point> points = new ArrayList<>();
        double[][] p = {{2.,1.},{4.,1.},{5.,2.},{3.,3.},{1.,2.},{2.,2.},{3.,2.},{2.,1.5}};
        int index = 0;
       for(int i=0;i<p.length;i++){
           if(p[i][1] <= p[index][1]){
               if(p[i][1] < p[index][1] || p[i][1]==p[index][1] && p[i][0] < p[index][0])
                   index = i;
           }
           points.add(new Point(p[i]));
       }
       points.set(0,new Point(p[index]));
       points.set(index,new Point(p[0]));

       for(int i = 1;i<points.size();i++){
           points.set(i,points.get(i).subtract(points.get(0)));
       }
        List<Point> subset = points.subList(1,points.size());
        subset.sort((o1,o2)->{
           double cos1 = o1.getX() / Math.sqrt(Math.pow(o1.getX(),2)+Math.pow(o1.getY(),2));
           double cos2 = o2.getX() / Math.sqrt(Math.pow(o2.getX(),2)+Math.pow(o2.getY(),2));
           if(cos1 > cos2 || cos1 == cos2 && (o1.getX() > o2.getX() || o1.getY() > o2.getY()))
               return -1;
           return 1;
       });
        for(int i = 1;i<points.size();i++){
            points.set(i,points.get(i).add(points.get(0)));
        }
        GrahamScan exa = new GrahamScan(points);
        exa.construct();


    }
    public GrahamScan(List<Point> points){
        this.points = points;
//        for(Point e: points){
//            System.out.println(e.getX() + " "+ e.getY());
//        }
    }
    public void construct(){
        Stack<Point> s = new Stack<>();
        s.push(points.get(0));
        s.push(points.get(1));
        s.push(points.get(2));
        for(int i=3;i<points.size();i++){
            Point top = s.peek();
            Point next_top = s.elementAt(s.size() -2);
            while(SegmentsIntersects.vectorMul(points.get(i).subtract(top),top.subtract(next_top)) > 0) {
                s.pop();
                top = s.peek();
                next_top = s.elementAt(s.size() -2);
            }

            s.push(points.get(i));
        }
        for(Point e:s)
            System.out.println(e.getX() +" "+ e.getY());
    }
}
