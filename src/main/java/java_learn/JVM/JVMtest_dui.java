package java_learn.JVM;

import java.util.ArrayList;
import java.util.List;

public class JVMtest_dui {
    byte bytes[]=new byte[1024*100];

    public static void main(String[] args) throws Exception{
        Thread.sleep(2000);
        System.out.println("begin");
        test();
    }

    public static void test()throws Exception{
        Thread.sleep(5000);
        List list=new ArrayList<>();
        System.out.println("------------start-----------");
        for(int i=0;i<2000;i++){
            Thread.sleep(16);
            list.add(new JVMtest_dui());
        }
    }
}
