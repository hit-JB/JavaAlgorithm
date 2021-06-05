package MutiThread;
import javax.print.attribute.standard.MediaSize;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {

                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY));
                    }
                } catch (InterruptedException e) {
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
class Bank{
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();
    public Bank(int n,double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
    }
    public void transfer(int from,int to ,double amount){
        bankLock.lock();
        try {

            if (accounts[from] < amount)
                return;
            System.out.print(Thread.currentThread() + ": ");
            accounts[from] -= amount;
            System.out.printf(amount + " from " + from + " " + to);
            accounts[to] += amount;
            System.out.println("Total balance" + getTotalBalance());
        }
        finally {
            bankLock.unlock();
        }
    }
    public double getTotalBalance(){
        double sum=0;
        for(double e:accounts)
            sum +=e;
        return sum;
    }
    public int size(){
        return accounts.length;
    }
}