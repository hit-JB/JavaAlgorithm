package simpleframe;
import java.awt.*;
import javax.swing.*;
public class SimpleFrameTest {
    public static void main(String[] args){
       Toolkit kit = Toolkit.getDefaultToolkit();
       Dimension screenSize = kit.getScreenSize();
       int width = screenSize.width;
       int height = screenSize.height;
       System.out.println("Width "+ width+" Height "+height);
    }
}
class SimpleFrame extends  JFrame{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    public SimpleFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
