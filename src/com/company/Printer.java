package com.company;

public class Printer implements Product,Output{
    static final int MAX_CACHE_LINE = 100;
    private String[] printDate = new String[MAX_CACHE_LINE];
    private int dataNum = 0;
    public void out(){
        while(this.dataNum > 0){
            System.out.println("Printing "+this.printDate[0]);
            System.arraycopy(this.printDate,1,this.printDate,0,--this.dataNum);

        }
    }
    public void getData(String msg){
        if (this.dataNum > MAX_CACHE_LINE){
            System.out.println("The buffer is full,add failed");
        }
        else{
            this.printDate[this.dataNum++] = msg;
        }
    }
    @Override
    public int getProduceTime() {
        return initialTime;
    }

    @Override
    public void getMethod() {
        System.out.println("The value of Output is "+OutputTime);
    }

    public static void main(String[] args){
        Printer o = new Printer();
        o.getData("this");
        o.getMethod();
        System.out.println("This is a example of implement interface:"+o.getProduceTime());

    }




}
interface Output{
    int OutputTime = 100;
    abstract  void getMethod();
}
interface Product{
    int initialTime = 10;
    abstract  int getProduceTime();
}
