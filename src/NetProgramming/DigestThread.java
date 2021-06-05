package NetProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.datatype.DatatypeFactory;

public class DigestThread extends Thread{
    private String filename;
    public DigestThread(String filename){
        this.filename = filename;
    }
    @Override
    public void run() {
        try {
                FileInputStream in = new FileInputStream(filename);
                MessageDigest sha = MessageDigest.getInstance("SHA-256");
                DigestInputStream din = new DigestInputStream(in,sha);
                while(din.read() != -1);
                din.close();
                byte[] digest = sha.digest();
                StringBuilder result = new StringBuilder(filename);
                result.append(": ");
                result.append(digest);
                System.out.println(result);
            } catch (FileNotFoundException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
