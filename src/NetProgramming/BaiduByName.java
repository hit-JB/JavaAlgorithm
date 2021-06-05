package NetProgramming;
import java.net.*;
public class BaiduByName {
    public static void main(String[] args){
        try
        {
            InetAddress[] address = InetAddress.getAllByName("www.hitsz.edu.cn");
            InetAddress me = InetAddress.getLocalHost();
            System.out.println("me: "+me);
            for(InetAddress e:address)
                System.out.println(e);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
