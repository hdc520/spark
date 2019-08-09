package java_spark.spark_book.第6章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.util.LongAccumulator;

public class accumulatorLine {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("accumulatorLine").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String>inputRDD=sc.textFile("/home/hdc/data/file.txt");
        final LongAccumulator accum=sc.sc().longAccumulator();
        accum.setValue(1);//设置累加器的初始值
        JavaRDD<Integer> accumRDD=inputRDD.map(new Function<String, Integer>() {
            public Integer call(String s) throws Exception {
                if(s.equals(""))
                    return 1;
                return 0;
            }
        });
        accumRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                accum.add(integer);
            }
        });
        System.out.println(accum.value());
    }
}
