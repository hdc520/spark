package java_spark.spark_book.第6章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;
//同个节点中的同种类型的多个任务对一个节点上的一个变量进行共享性操作（只读）
//在工作节点work中会有多个executor进程，每个executor运行多个task任务，算子的函数执行由多个task任务组成
//因此函数内使用到的外部变量会拷贝到执行这个函数的每一个task任务中，这样会影响性能
//此时出现共享变量（广播变量的一种），将所需要到的外部变量变成共享变量，这样就会拷贝一份到每个节点里，而不是每一个任务里。
public class broadcast_var {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("broadcast_var").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        int factor=3;
        final Broadcast<Integer>factorBroadcast=sc.broadcast(factor);//只读
        List<Integer> list= Arrays.asList(1,2,3,4,5,6);
        JavaRDD<Integer>inputRDD=sc.parallelize(list);
        JavaRDD<Integer>listRDD=inputRDD.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) throws Exception {
                return integer*factorBroadcast.value();
            }
        });
        listRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
