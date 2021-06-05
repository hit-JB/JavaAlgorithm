package CompileTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class CompileClassLoader extends ClassLoader{
    private  byte[] getByte(String filename)
        throws IOException
    {
        File file = new File(filename);
        long len = file.length();
        byte[] raw =new byte[(int) len];
        try (
            FileInputStream fin =new FileInputStream(file)) {
            int r = fin.read(raw);
            if (r != len)
                throw new IOException("can't read the full file" + r + "!=" + len);
            return raw;
        }

    }
    private boolean compile(String javaFile) throws IOException{
        System.out.println("Compile Class Loader:"+javaFile+"...");
        Process p = Runtime.getRuntime().exec("javac"+javaFile);
        try {
            p.waitFor();
        }
        catch (InterruptedException ie){
            System.out.println(ie);
        }
        int ret = p.exitValue();
        return ret ==0;
    }
    protected Class<?> findClass(String name){
        Class clazz = null;
        String fileStub = name.replace(".","/");
        String javaFilename = fileStub+".java";
        String classFilename = fileStub+".class";
        File javaFile = new File(javaFilename);
        File classFile =new File(classFilename);
        if(javaFile.exists() && (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())){
            try{
                if(!compile(javaFilename) || !classFile.exists()){
                    throw new ClassCastException("ClassNotFoundException:"+javaFilename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(classFile.exists()){
            try{
                byte[] raw = getByte(classFilename);
                clazz = defineClass(name,raw,0, raw.length);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(clazz == null){
            throw new ClassCastException(name);
        }
        return clazz;
    }
    public static void main(String[] args) throws Exception {
        if(args.length  <1) {
            System.out.println("The parameters is not enough");
            System.out.println("java CompileClassLoader ClassName");
        }
            String progClass = args[0];
            String[] progArgs = new String[args.length-1];
            System.arraycopy(args,1,progArgs,0,progArgs.length);
            CompileClassLoader cc1 = new CompileClassLoader();
            Class<?> clazz = cc1.loadClass(progClass);
            Method main = clazz.getMethod("main",(new String[0]).getClass());
            Object argsArray[] = {progArgs};
            main.invoke(null,argsArray);

    }
}
