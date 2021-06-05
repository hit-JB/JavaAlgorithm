package com.company;

public class StaticInnerClassTest {
    public static void main(String[] args){
        double[] values={1,2,3,4,5,6};
        ArrayAlg.Pair pair = ArrayAlg.minmax(values);
        System.out.println("The values is :"+values.toString()+" the min is :"+pair.getFirst()+" the max is"+pair.getSecond());

    }
}
class ArrayAlg{
    public static  class  Pair {
        private double first;
        private double second;
        public Pair(double first,double second){
            this.first=first;
            this.second=second;
        }
        double getFirst(){
            return this.first;
        }
        double getSecond(){
            return this.second;
        }
    }
    public static Pair minmax(double[] values){
        double min=Double.POSITIVE_INFINITY;
        double max=Double.NEGATIVE_INFINITY;
        for(double v:values){
            if(min>v) min=v;
            if(max<v) max =v;
        }
        return new Pair(min,max);
    }
}
