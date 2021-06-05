package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class Interfacetest {
    public static void addable(Runnable f){
        System.out.println("Test the Runnable 5,3:"+f.compare(3,35));
    }
    public static void main(String args[]){
        Runnable r = Integer::compare;
        String[] s = {"JB","HIT","HJL","A"};
        com f = new com();
        Arrays.sort(s,f);
        f.disp();
        for(String e:s){
            System.out.println(e);
        }

    }
}
interface Runnable{
    int compare(int a,int b);
}
class com implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
       return ((String) o1).length() -((String) o2).length();
    }
    public void disp(){
        System.out.println("This is a test");
    }

}
