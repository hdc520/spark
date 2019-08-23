package java_learn.JMM;

public class JMM_test {
    private static boolean initFlag=false;
    public static void main(String[] args) throws Exception{
        Thread t=Thread.currentThread();
        System.out.println("main线程："+t.getClass().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新建线程1："+this.getClass().getName());
                System.out.println("waiting data...");
                while (!initFlag){

                }
                System.out.println("--------success");
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新建线程2："+this.getClass().getName());
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
