package com.company;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public class InnerTest {
    public static void main(String[] args ){

    }

}
class TalkingClock{
    private int interval;
    private boolean beep;
    public TalkingClock(int interval,boolean beep){
        this.interval = interval;
        this.beep = beep;

    }
    public void start(){

        Timer t = new Timer(interval,event->{
            System.out.println("At the tone,the time is"+new Date());
            if(beep) Toolkit.getDefaultToolkit().beep();
        });
        t.start();
    }
    class TimerPrinter implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone,the time is "+new Date());
            if(beep) Toolkit.getDefaultToolkit().beep();
        }
    }

}


