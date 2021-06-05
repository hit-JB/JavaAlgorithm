package NetProgramming;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheckTest {
    public static final String BLACKHOLE = "https://www.google.com/";
    public static void main(String[] args){
        for(String arg:args){
            System.out.println(arg);
            if(isSpammer(arg)){
                System.out.println(arg+ "is a know spammer.");
            }
            else{
                System.out.println(arg+ " appears legitimate");
            }
        }
    }
    private static boolean isSpammer(String arg){
        try{
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for(byte octet:quad){
                int unsihnedByte = octet<0?octet+256:octet;
                query = unsihnedByte +"."+ query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
           return false;
        }
    }
}
