package JavaAlgorithm.DynamicProgramming;

public class MulEle <T,E>{
    private T e1;
    private E e2;
    public MulEle(T e1, E e2){
        this.e1 = e1;
        this.e2 =e2;
    }

    public T getE1() {
        return e1;
    }
    public E getE2(){
        return e2;
    }
}
