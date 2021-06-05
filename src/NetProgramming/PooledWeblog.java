package NetProgramming;

import java.io.*;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class PooledWeblog {
    private final static int NUM_THREADS = 4;
    public static void main(String[] args) throws FileNotFoundException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        Queue<LogEntry> results = new LinkedList<LogEntry>();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]),"UTF=8"));){
            for(String entry = in.readLine();entry!=null;entry = in.readLine()){
                LookupTask task = new LookupTask(entry);
                Future<String> future = executor.submit(task);
                LogEntry result = new LogEntry(entry,future);
                results.add(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(LogEntry result:results){
            try{
                System.out.println(result.future.get());
            }
            catch (InterruptedException | ExecutionException e) {
                System.out.println(result.original);
            }
        }
    }
    private static class LogEntry{
        String original;
        Future<String> future;
        LogEntry(String original,Future<String> future){
            this.original = original;
            this.future = future;
        }
    }
}
class LookupTask implements Callable<String>{
    private String line;
    public LookupTask(String line){
        this.line = line;
    }
    @Override
    public String call() throws Exception {
        try {
            int index = line.indexOf(' ');
            String address = line.substring(0,index);
            String theRest = line.substring(index);
            String hostname = InetAddress.getByName(address).getHostName();
            return hostname+" " + theRest;
        }catch (Exception e){
            return line;
        }
    }
}
