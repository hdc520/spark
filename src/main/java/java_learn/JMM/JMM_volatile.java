package java_learn.JMM;

public class JMM_volatile {
    private static volatile boolean initFlag=false;
    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waiting volatile data...");
                while (!initFlag){

                }
                System.out.println("--------success");
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                prepareData();
            }
        }).start();
    }
    public static void prepareData(){
        System.out.println("start--prepare--data--");
        initFlag=true;
        System.out.println("finish--prepare--data--");
    }
}
