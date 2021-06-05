package JavaAlgorithm.ComputeGeometry;

import java.util.Map;
public class Point implements Comparable<Point> {
    private double x;
    private double y;
    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }
    public Point(double[] p){
        this.x = p[0];
        this.y = p[1];
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return  y;
    }
    public double norm(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    public Point add(Point other){
        return new Point(x+other.x,y+ other.y);
    }
    public Point subtract(Point other){
        return new Point(x- other.x,y-other.y);
    }

    @Override
    public int compareTo(Point o) {
         if(this.x == o.x && this.y==o.y)
             return 1;
         return 0;
    }
}
