package Utile;
import javax.net.ssl.SSLContext;
import java.util.*;
import java.lang.reflect.*;
public class ReflectionTest {
    public static void main(String[] args){
        String name;
        if(args.length > 0) name = args[0];
        else{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the class name:");
            name = in.next();
        }
        try{
            Class<?> cl = Class.forName(name);
            Class superCl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length() >0) System.out.print(modifiers+" ");
            System.out.print("Class "+name);
            if(superCl !=null &&superCl != Object.class)System.out.println("extends "+superCl.getName());
            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethod(cl);
            System.out.println();
            System.out.println("..........");
            printFields(cl);
            System.out.println("..........");
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void printConstructors(Class cl){
        Constructor[] constructors = cl.getConstructors();
        for(Constructor c:constructors){
            String name = c.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length() > 0)System.out.print(modifiers + "");
            System.out.print(name+"(");
            Class[] paramTypes = c.getParameterTypes();
            for(int j=0;j< paramTypes.length;j++){
                if(j > 0)System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(')');
        }
    }
    public static void printMethod(Class cl){
        Method[] methods = cl.getMethods();
        for(Method m:methods){
            Class retType = m.getReturnType();
            String name = m.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(m.getModifiers());//m.getModifiers reflect the type of the m {public private protected}
            if(modifiers.length() > 0)System.out.print(modifiers+" ");
            System.out.print(retType.getName()+ " "+ name+"(");
            Class[] paramTypes = m.getParameterTypes();
            for(int j=0;j<paramTypes.length;j++){
                if(j>0)System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
          }
        }
    public static void printFields(Class cl) {
        Field[] fields = cl.getFields();
        for(Field f:fields){
            Class type = f.getType();
            String name = f.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(f.getModifiers());
            if(modifiers.length() > 0)System.out.print(modifiers+" ");
            System.out.println(type.getName()+" "+name+";");
    }
    }
}
