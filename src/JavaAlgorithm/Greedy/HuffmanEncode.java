package JavaAlgorithm.Greedy;

import java.lang.management.MemoryUsage;
import java.lang.management.MonitorInfo;
import java.util.*;

import JavaAlgorithm.DynamicProgramming.*;
import com.company.Interfacetest;

import javax.swing.*;

public class HuffmanEncode {
    static int i = 0;
    public static void main(String[] args){
        List<Tree<String,Integer>> rowCode = new ArrayList<>();
        int[] frequent = {7,2,3,1,5,6};
        String[] label = {"A","B","C","D","E","F"};
        for(int i=0;i<frequent.length;i++){
            rowCode.add(new Tree<>(label[i],frequent[i]));
        }
        //MulEle<Tree<String,Integer>,Tree<String,Integer>> min12 = getNode12(rowCode);
        Tree<String,Integer> encodeRowCode = getHuffmanTree(rowCode);
        Tree<String,Integer> p,l,r;
        traverseTree(encodeRowCode);
    }
    public static void traverseTree(Tree<String,Integer> node){
        if(node.getLeft() != null) {
            i++;
            traverseTree(node.getLeft());
        }
        if(node.getLabel().length() == 1)
            System.out.println("key: "+node.getLabel()+" encode "+i);
        if(node.getRight() != null){
            i++;
            traverseTree(node.getRight());
        }
        i--;
    }
    public static Tree<String,Integer> getHuffmanTree(List<Tree<String,Integer>> data){
        while(data.size() > 1){
            MulEle<Tree<String,Integer>,Tree<String,Integer>> min12 = getNode12(data);
            Tree<String,Integer> min1 = min12.getE1();
            Tree<String,Integer> min2 = min12.getE2();

            data.remove(min1);

            data.remove(min2);

            data.add(new Tree<>(min1 , min2,min1.getLabel()+min2.getLabel(),
                    min1.getValue()+min2.getValue()));
        }
        return data.get(0);
    }
    public static MulEle<Tree<String,Integer>,Tree<String,Integer>> getNode12(List<Tree<String,Integer>> data){
        data.sort(Comparator.comparingInt(Tree::getValue));
        Tree<String,Integer> min1 = data.get(0);
        Tree<String,Integer> min2 = data.get(1);
//
//        for(Tree<String,Integer> e : data){
//            if(e.getValue().compareTo(min2.getValue())<=0){
//                if(e.getValue().compareTo(min1.getValue()) <= 0){
//                    min2 = min1;
//                    min1 = e;
//                }
//                else{
//                    min2 = e;
//                }
//            }
//        }
//        System.out.println("min1 "+ min1.getLabel()+ " "+min1.getValue());
//        System.out.println("min2 "+ min2.getLabel()+ " "+min2.getValue());
//        System.out.println();
        return new MulEle<>(min1,min2);
    }
    public static void test(List<? super Integer> x){
        for(int i=0;i<10;i++){
            x.add(i);
        }
    }
    public static<T ,E extends Comparable> MulEle<Map.Entry<T,E>,Map.Entry<T,E>> getMin1_2(Set<Map.Entry<T,E>> entrySet){
        Map.Entry<T,E> min1=null,min2=null;
        for(Map.Entry<T,E> entry: entrySet)
        {
            min1 = entry;
            min2 = entry;
            break;
        }
        for(Map.Entry<T,E> entry:entrySet)
        {
            if(entry.getValue().compareTo(min1.getValue()) < 0){
                min2 = min1;
                min1 = entry;
            }
        }
        return new MulEle(min1,min2);
    }

}
class Tree<T,E extends Comparable<E>> implements Comparable<Tree<T,E>>{
    private Tree<T,E> left;
    private Tree<T,E> right;
    private T label;
    private E value;
    Tree(){
        left = null;
        right = null;
        label = null;
        value = null;
    }
    Tree(Tree left,Tree right,T label,E frequent){
        this.left = left;
        this.right = right;
        this.label = label;
        this.value = frequent;
    }
    Tree(T label,E frequent){
        left = null;
        right = null;
        this.label = label;
        this.value = frequent;
    }
    public E getValue() {
        return value;
    }

    public T getLabel() {
        return label;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public void setLabel(T label) {
        this.label = label;
    }

    public void setValue(E value) {
        this.value = value;
    }


    @Override
    public int compareTo(Tree o) {
        return this.getValue().compareTo( (E) o.getValue());
    }

}
