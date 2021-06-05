package cn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class broadClassTest {
    public static void main(String args[]){
        Collection<shape> a = new ArrayList<>(10);
        Collection<Circle> b = new ArrayList<>(10);
        List<Object> ob = new ArrayList<>(10);
        for(int i =0;i<3;i++){
            ob.add(new Circle());
            b.add(new Circle());
        }
        for(Object e:ob){
            ((Circle) e).draw(new Canvas());
        }
        System.out.println();
        for (Circle e:b){
            Object ob_copy = e;
            e.draw(new Canvas());
            ((Circle) ob_copy).draw(new Canvas());
        }

    }
    static <T> T copy(List<? super T> father,List<T> child){
        T last=null;
        for(T e:child){
            last = e;
            father.add(e);
        }
        return last;

    }
    static <T,S extends T> void fromArrayToCollection(Collection<S> a , Collection<T> c){
        for(T e:a){
            c.add(e);
        }
        System.out.println(c);
        for(T e:c){
            System.out.println("This is a test"+e);
        }
    }

}
abstract class shape{
    public abstract void draw(Canvas c);
}
class Circle extends shape{
    @Override
    public void draw(Canvas c) {
        System.out.println("Draw a circle "+c);
    }
}
class Rectangle extends shape{
    @Override
    public void draw(Canvas c){
        System.out.println("Draw a rectangle"+c);
    }
}
class Canvas{
    public void drawAll(List<? extends shape> shapes){
        System.out.println("Run"+shapes.size());
        for(shape e:shapes){
            System.out.println(1);
            e.draw(this);
        }
    }
}
