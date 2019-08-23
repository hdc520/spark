package java_spark.spark_book.第10章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//基于hdfs文件的实时wordcount程序
public class HDFSWordcount {
    public static void main(String[] args) throws Exception{
        SparkConf conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
        JavaStreamingContext streamingContext=new JavaStreamingContext(conf, Durations.seconds(8));

        //首先通过JavaStreamingContext中的filestream()方法针对HDFS目录创建输入数据流
        JavaDStream<String>lines=streamingContext.textFileStream("hdfs://localhost:9000/data");
        //然后执行wordsCOunt操作
        JavaDStream<String>words=lines.flatMap(new FlatMapFunction<String, String>() {

            public Iterator<String> call(String s) throws Exception {
                String word[]=s.split(" ");
                List<String> list=new ArrayList<>();
                for (String str:word) {
                    list.add(str);
                }
                return list.iterator();
            }
        });
        JavaPairDStream<String,Integer> wordPairs=words.mapToPair(new PairFunction<String, String, Integer>() {

            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        });
        JavaPairDStream<String,Integer>wordCounts=wordPairs.reduceByKey(new Function2<Integer, Integer, Integer>() {

            public Integer call(Integer s1, Integer s2) throws Exception {
                return s1+s2;
            }
        });


        wordCounts.print();
        streamingContext.start();
        streamingContext.awaitTermination();
        streamingContext.close();
    }
}
