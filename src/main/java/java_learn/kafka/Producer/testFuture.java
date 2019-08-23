package java_learn.kafka.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class testFuture {
    public static void main(String[] args) throws Exception{
        Thread.sleep(1000);
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("i= " + i);
                }
            }
        });
        future.get();
        System.out.println("------------------------------------");
        executor.shutdown();
    }
}
