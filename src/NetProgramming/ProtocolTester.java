package NetProgramming;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class ProtocolTester {
    public static void main(String[] args){
        testProtocol("https://leetcode-cn.com/problems/next-permutation/");

    }
    private static void testProtocol(String url){
        try{
            URL u = new URL(url);
            InetAddress address = InetAddress.getByName(u.getHost());
            System.out.println("address "+address.getHostAddress());
            Class<?>[] types = new Class[3];
            System.out.println("protocol "+ u.getProtocol());
            System.out.println("Hosts "+u.getHost());
            System.out.println("Port "+u.getPort());
            System.out.println("Ref "+u.getRef());
            System.out.println("Path "+u.getPath());
            System.out.println("Query "+ u.getQuery());
            types[0] = String.class;
            types[1] = Reader.class;
            types[2] = InputStream.class;
            Object o = u.getContent(types);
            if(o instanceof String){
                System.out.println(o);
            }
            else if(o instanceof Reader){
                int c;
                Reader r = (Reader) o;
                while((c = r.read())!=-1) System.out.print((char) c);
                r.close();
            }else if(o instanceof InputStream){
                int c ;
                InputStream in = (InputStream) o;
                while((c = in.read())!=-1) System.out.write(c);
            }else{
                System.out.print("Error: Unexpected type "+o.getClass());
            }


        }catch (MalformedURLException ex){
            String protocol = url.substring(0,url.indexOf(':'));
            System.out.println(protocol+ " is not supported");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
