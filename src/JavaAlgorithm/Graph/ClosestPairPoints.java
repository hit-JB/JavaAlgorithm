package JavaAlgorithm.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClosestPairPoints {
    static final double MAX_DIST = Double.MAX_VALUE;
    public static void main(String[] args){
        double[] x={1,2,3,4,5,6};
        double[] y= {0,0.5,1.1,1.2,1.5,0.2};
        List<Pair<Double,Double>> x_center = new
                ArrayList<>();
        for(int i=0;i<x.length;i++)
            x_center.add(new Pair<>(x[i],y[i]));
        x_center.sort(Comparator.comparing(Pair::getY));
        for(Pair<Double,Double> e:x_center)
            System.out.println(" x: "+e.getX()+" y: "+e.getY());
        double min_dist = getSol(x.length,x,y);
        System.out.println("min dist: "+min_dist);

    }
    public static double getSol(int p,double[] x,double[] y){
        if(p <= 3)
            return force(p,x,y);
        int half = p / 2;
        double[] x_left = new double[half],x_right = new double[p-half],
                y_left = new double[half],y_right = new double[p-half];
        for(int i=0;i<p;i++){
            if(i < half){
                x_left[i] = x[i];
                y_left[i] = y[i];
            }
            else{
                x_right[i-half] = x[i];
                y_right[i-half] = y[i];
            }
        }
        double distL = getSol(half,x_left,y_left),distR = getSol(p-half,x_right,y_right);
        double delta = Math.min(distL,distR);
        double line = x[half];
        double min = delta;
        List<Pair<Double,Double>> x_center = new
                ArrayList<>();
        for(int i=0;i<x.length;i++){
            if(Math.abs(x[i] - line) > delta)
                continue;
            x_center.add(new Pair<>(x[i],y[i]));
        }
        x_center.sort(Comparator.comparing(Pair::getY));

        for(int i=0;i<x_center.size();i++)
        {
            for(int j=i+1;j<x_center.size() && j<i+8;j++)
            {
                Pair<Double,Double> e1 = x_center.get(i);
                Pair<Double,Double> e2 = x_center.get(j);
                double dist = Math.sqrt(Math.pow(e1.getX() - e2.getX(),2) + Math.pow(e1.getY() - e2.getY(),2));
                if(dist < min)
                    min = dist;
            }
        }
        return min;
    }
    public static double force(int p,double[] x,double[] y){
        double min = MAX_DIST;
        for(int i=0;i<p;i++)
        {
            for(int j= i+1;j<p;j++)
            {
                double dist = Math.sqrt(Math.pow(x[i] -x[j],2)+Math.pow(y[i]-y[j],2));
                if(dist < min)
                    min = dist;
            }
        }
        return min;
    }
}
class Pair<T extends Number,E extends Number> implements Comparable<Pair<T,E>> {
    private T x;
    private E y;
    public Pair(T e1,E e2)
    {
        x = e1;
        y = e2;
    }

    public T getX() {
        return x;
    }

    public E getY() {
        return y;
    }

    @Override
    public int compareTo(Pair<T, E> o) {
       if(x.doubleValue() >= o.x.doubleValue()) {
           if (x.doubleValue() > o.x.doubleValue())
               return 1;
           if(x.doubleValue() == o.x.doubleValue() && y.doubleValue() > o.y.doubleValue())
               return 1;
           if(x.doubleValue() == o.x.doubleValue() && y.doubleValue() == o.y.doubleValue())
               return 0;
           else
               return -1;
       }
       return -1;
    }
}
