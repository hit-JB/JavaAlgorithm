package NetProgramming;
import java.io.File;
import java.time.*;
public class CallBackTest {
    public static void main(String[] args) throws InterruptedException {
        int[] time = {10000,11000,12000};
        for(int i=0;i<time.length;i++){
            Thread t= new Thread(new timeSleep(time[i]));
            t.start();
        }
    }
    public static void printTime(double time){
        System.out.println("Time enough: "+time);
    }
}
class timeSleep implements Runnable{
    private int sleepTime;
    public timeSleep(int sleepTime){
        this.sleepTime = sleepTime;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(sleepTime);
            CallBackTest.printTime(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
