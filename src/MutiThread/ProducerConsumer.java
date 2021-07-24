package MutiThread;

public class ProducerConsumer {
    static int N=100;
    static producer p = new producer();
    static consumer c = new consumer();
    static our_monitor mon = new our_monitor();
    public static void main(String[]args) {
        p.start();
        c.start();
    }
    static class producer extends Thread{
        public void run()
        {
            int item;
            while(true)
            {
                item = produce_item();
                mon.insert(item);
            }
        }
        private int produce_item(){
            return (int)(Math.random() * 100);
        }
    }
    static class consumer extends Thread{
        public void run(){
            int item;
            while(true)
            {
                item = mon.remove();
                consumer_item(item);
            }
        }
        public void consumer_item(int item)
        {
            System.out.println("This is is consumer program: "+ item);
        }
    }
    static class our_monitor{
        private int[] buffer = new int[N];
        private int count = 0,lo=0,hi=0;
        public synchronized void insert(int val)
        {
            if(count == N) {
                System.out.println("Block the insert");
                go_to_sleep();
            }
            buffer[hi] = val;
            hi = (hi+1) % N;
            count =count+1;
            if(count == 1) notify();

        }
        public synchronized int remove(){
            int val;
            if(count ==0) {
                System.out.println("Block the remove");
                go_to_sleep();
            }
            val = buffer[lo];
            lo = (lo+1) % N;
            count = count-1;
            if(count ==N-1)notify();
            return val;
        }
        private void go_to_sleep()
        {
            try {
                wait();
            }
            catch (InterruptedException exc)
            {};
        }
    }


}
