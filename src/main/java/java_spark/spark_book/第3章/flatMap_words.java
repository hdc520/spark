package java_spark.spark_book.第3章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class flatMap_words {
    public static void main(String []args){
        SparkConf conf=new SparkConf().setAppName("flatMap_words").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> inputRDD=sc.textFile("/home/hdc/word");
        JavaRDD<String> wordRDD=inputRDD.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                String []words=s.split(" ");
                List<String> list=new ArrayList<String>();
                for(String str:words)
                    list.add(str);
                return list.iterator();
            }
        });

        System.out.println(wordRDD.collect().toString());
    }
}
