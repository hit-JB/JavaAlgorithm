package com.company;

public class PairTest1 {
    public static void main(String[] args){
        String[] words = {"Marry","had","a","little","lamb"};
        Pair<String> mm = ArrayAlg1.minmax(words);
        Pair<Main.Employee> e = new Pair<>(new Main.Employee(),new Main.Employee());

        if (e instanceof Pair<Main.Employee>){
            System.out.println("True");
        }
    }
}
class ArrayAlg1{
    public static Pair<String> minmax(String[] a){
        if (a==null || a.length ==0) return null;
        String min=a[0];
        String max=a[0];
        for(String e:a){
            if(min.compareTo(e) > 0) min = e;
            if(max.compareTo(e) < 0) max = e;
        }
        return new Pair<>(min,max);
    }
    public static<T> T getMiddle(T[] a){
        return a[a.length /2];
    }
}
class Pair<T>{
    private T first;
    private T second;
    public Pair(){first = null;second=null;}
    public Pair(T first,T second){
        this.first = first;
        this.second=second;
    }
    public T getFirst(){
        return this.first;
    }
    public T getSecond() {
        return this.second;
    }
    public void setFirst(T first){
        this.first=first;
    }
    public void setSecond(T second) {
        this.second = second;
    }
}