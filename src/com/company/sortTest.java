package com.company;
import java.util.*;
public class sortTest {
    public static <T extends Comparable> void insertionSort(List<T> a) {
        for (int i = 0; i < a.size()-1; i++) {
            T key = a.get(i+1);
            int j = i;
            while(j>=0 && key.compareTo(a.get(j)) > 0){
              a.set(j + 1, a.get(j));
              a.set(j, key);
              key = a.get(j--);
            }
        }
    }
    public static void main(String args[]){
        int[] a = {1,2,3,4,5,6,5,4,3,2,1,0};
        String[] b ={"JB","HIT","JIG","J","LKHGD"};
        ArrayList<Name> B = new ArrayList<>();
        for(String e : b){
            B.add(new Name(e));
        }
        ArrayList<Integer> A = new ArrayList<Integer>();
        for(int e:a){
            A.add(e);
        }
        sortTest.insertionSort((List) B);
        for(Name e : B){
            System.out.println("Array list test "+e.getName());
        }


    }
}
class Name implements Comparable{
    private final String name;
    public Name(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.length() - ((Name) o).getName().length();
    }
}

