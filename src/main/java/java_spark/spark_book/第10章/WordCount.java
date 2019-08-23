package java_spark.spark_book.第10章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WordCount {
    public static void main(String[] args) throws Exception{
        //创建sparkconf对象
        SparkConf conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");

        //创建javastreamingcontext对象,每隔2秒划分一个batch
        JavaStreamingContext streamingContext=new JavaStreamingContext(conf, Durations.seconds(2));

        //创建输入Dstremam，代表从数据源（kafka，socket）获取的持续不断的数据流
        //JavaReceiverInputDStream可以创建一个数据源为Socket网络端口的数据流，代表一个输入的Dstream。
        JavaReceiverInputDStream<String> lines=streamingContext.socketTextStream("localhost",9999);
        //对开始接受到的数据执行sparkcore计算，此计算执行应用在Dstream中即可
        //在底层实际上是对Dstream中的一个个RDD进行计算，计算过后会产生新的RDD作为新Dstream中的RDD

        //拆分单词
        JavaDStream<String>words=lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                List<String>list=new ArrayList<>();
                String word[]=s.split(" ");
                for(String str:word)
                    list.add(str);
                return list.iterator();
            }
        });
        //每个单词作为key，值为1
        JavaPairDStream<String,Integer>pairDStream=words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        });

        JavaPairDStream<String,Integer>wordcounts=pairDStream.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        //Spark本身是函数式编程的计算模型，所有在words和pair Dstream中，没法在实例变量中进行缓存
        //此时只能将最后计算出的结果打印处来，并休眠5秒钟以便观察
        Thread.sleep(5000);
        wordcounts.print();

        //对JavaStreamingContext进行预处理
        streamingContext.start();
//        调用StreamingContext的awaitTermination()方法，来等待应用程序的终止
        streamingContext.awaitTermination();
        streamingContext.close();
    }
}

