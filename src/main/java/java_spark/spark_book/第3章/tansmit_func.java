package java_spark.spark_book.第3章;

//在create_transform_RDD中使用的匿名函数传递函数
//现在使用具名函数传递

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class tansmit_func {

    static class containhdc implements Function<String,Boolean>{
        public Boolean call(String s){
            return s.contains("hdc");
        }
    }

    public static void main(String[] args){
        SparkConf conf=new SparkConf().setAppName("tansmit_func").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> inputRDD=sc.textFile("/home/hdc/word");
        JavaRDD<String> hdcRDD=inputRDD.filter(new containhdc());
    }

}
