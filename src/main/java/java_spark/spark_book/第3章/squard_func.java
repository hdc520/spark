package java_spark.spark_book.第3章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;

public class squard_func {
    public static void main(String args[]){
        SparkConf conf=new SparkConf().setAppName("squard_func").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<Integer> inputRDD=sc.parallelize(Arrays.asList(1,2,3,4));
        JavaRDD<Integer> squardRDD=inputRDD.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) throws Exception {
                return integer*integer;
            }
        });

        System.out.println(squardRDD.collect().toString());
    }
}
