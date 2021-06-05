package NetProgramming;

import java.io.*;

public class OutputTest {
    public static void main(String[] args) throws IOException {
        OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream("./Data/test.txt"),"utf-8");
        w.write("Hello this is a output test");
        w.flush();
    }
}
