package com.Interface;

import java.util.ArrayList;
import java.util.List;

public class InterfaceTest {
     public static void main(String[] args) {
        DictTest<Integer> a= new DictTest<>(10);
        DictTest a_copy = a;
        int[] b ={1,2,3,4,5,6};
        for(int e:b){
            a.add(""+e,e);
        }
        a_copy.traverse();

    }
}
interface dict<T,E>{
    void add(T key,E value);
    E find(T key);
    int length();
    void traverse();
}
class DictTest<E> implements dict<String,E>,Comparable<DictTest<E>>{
    private List<String> keys;
    private List<E> values;
    private final int size;
    DictTest(int initialSize){
        size = initialSize;
        keys = new ArrayList<>(initialSize);
        values = new ArrayList<>(initialSize);
    }
    @Override
    public void add(String key, E value) {
        keys.add(key);
        values.add(value);
    }

    @Override
    public E find(String key) {
        int index = 0;
        for(String k:keys){
            if(k==key)
                return values.get(index);
        }
        return null;
    }

    @Override
    public int length() {
        return keys.size();
    }
    @Override
    public void traverse(){
        for(int i=0;i<keys.size();i++){
            System.out.println("Key:"+keys.get(i)+" Value:"+values.get(i));
        }
    }


    @Override
    public int compareTo(DictTest<E> o) {
        return size-o.size > 0 ? 1:0;
    }
}