package NetProgramming;

import javax.net.ssl.SSLContext;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetInterfaceTest {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()){
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            System.out.println(ni.getDisplayName());
            while(address.hasMoreElements())
                System.out.println(address.nextElement());
        }
    }
}
