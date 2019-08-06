package java_spark.spark_book.第6章;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class accumulator {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("broadcast_var").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        final Accumulator<Integer> SumAccumulator=sc.accumulator(0);
        List<Integer> list= Arrays.asList(1,2,3,4);
        JavaRDD<Integer>InputRDD=sc.parallelize(list);
        InputRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                SumAccumulator.add(integer);
            }
        });

        System.out.println(SumAccumulator.value());
    }
}
