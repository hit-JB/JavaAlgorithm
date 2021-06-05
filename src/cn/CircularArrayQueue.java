package cn;

import java.util.Iterator;

public class CircularArrayQueue <E> implements Queue<E>,Collection<E>{
    public static void main(String args[]){
        CircularArrayQueue<String> s = new cn.CircularArrayQueue<String>(100);
        String [] a = {"a","b","c","d"};
        Iterator<String> iter = s.iterator();
        iter = (implementMethod<String>) iter;
        for(String e:a){
            ((implementMethod<String>) iter).add(e);
        }
        ((implementMethod<String>) iter).resetStr();
        while(iter.hasNext()){
            System.out.println("Element:"+iter.next());
        }
        System.out.println("Element:"+iter.next());


    }
    private int head;
    private int tail;
    private final int size;
    private E[] cup;
    CircularArrayQueue(int capacity){
        size = capacity;
        cup =  (E []) new Object[capacity];
        head = 0;
        tail = 0;
    }
    @Override
    public void add(E element) {
        cup[tail++]=element;
    }


    @Override
    public  Iterator<E> iterator() {
        return new implementMethod<E>(size);
    }

    @Override
    public E remove() {
        return cup[tail--];
    }

    @Override
    public int size() {
        return tail-head;
    }
}
interface  Queue<E>{
    void add(E element);
    E remove();
    int size();
}
interface Collection<E>{

    Iterator<E> iterator() ;

}
class implementMethod <E> implements Iterator<E>{
    private final int size;
    private int str;
    private E[] cup;
    implementMethod(int capacity){
        size = capacity;
        cup = (E[])new Object[capacity];
        for(int i=0;i<size;i++)
            cup[i] = null;
        str = 0;
    }
    public void add(E e){
        cup[str++] = e;
    }
    public void resetStr(){
        str = 0;
    }

    @Override
    public boolean hasNext() {
        return (str + 1) != size && cup[str+1] != null;
    }

    @Override
    public E next() {
        return cup[str++];
    }
}
