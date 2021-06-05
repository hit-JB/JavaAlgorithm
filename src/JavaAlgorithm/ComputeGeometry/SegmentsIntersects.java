package JavaAlgorithm.ComputeGeometry;

public class SegmentsIntersects {
    public static void main(String[] args){
        Point x1 = new Point(0,0.4);
        Point x2 = new Point(0.4,0);
        Point x3 = new Point(0,0);
        Point x4 = new Point(0.4,0.4);
        System.out.println("Line1 and Line2 intersect:?"
                +isSegmentsIntersects(x1,x2,x3,x4));
    }
    public static boolean isSegmentsIntersects(Point x1,Point x2,
                                             Point x3,Point x4){
        double d1 = direction(x1,x3,x4);
        double d2 = direction(x2,x3,x4);
        double d3 = direction(x3,x2,x1);
        double d4 = direction(x4,x2,x1);
        if(d1 * d2 < 0 && d3 * d4 < 0)
            return true;
        else
            if(d1 ==0 && inBox(x1,x3,x4))
                return true;
            else
                if(d2 ==0 && inBox(x2,x3,x4))
                    return true;
                else
                    if(d3 ==0 && inBox(x3,x2,x1))
                        return true;
                    else
                        if(d4==0 && inBox(x4,x2,x1))
                            return true;
                        else
                            return false;
    }
    public static boolean inBox(Point p1,Point p2,Point p3){
        Point x1 = p1.subtract(p2);
        Point x2 = p1.subtract(p3);
        return x1.getX()*x2.getX() + x1.getY() * x2.getY() >=0;
    }
    public static double direction(Point p1,Point p2,Point p3){

        return vectorMul(p1.subtract(p3),p2.subtract(p3));
    }
    public static double  vectorMul(Point x1,Point x2){
        return x1.getX() * x2.getY() - x2.getX() * x1.getY();
    }
}
