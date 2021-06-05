package com.company;

public class FinalizeTest {
    private  static FinalizeTest ft = null;
    public void info(){
        System.out.println("This is a test of the FinalizeTest");
    }
    public static void main(String args[]){
        new FinalizeTest();
        System.gc();
        Runtime.getRuntime().runFinalization();
        System.runFinalization();
        ft.info();
        ft = null;
        System.gc();
        Runtime.getRuntime().runFinalization();
        System.runFinalization();

    }
    public void finalize(){
        System.out.println("The time to final");
        ft = this;
    }
}
