package java_spark.spark_book.第10章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.*;
public class KafkaReceiverWordCount {
    public static void main(String[] args) throws Exception{

        //bin/kafka-server-start.sh -daemon config/server.properties启动kafka
        SparkConf conf=new SparkConf().setAppName("KafkaReceiverWordCount").setMaster("local[2]");
        JavaStreamingContext javaStreamingContext=new JavaStreamingContext(conf, Durations.seconds(1));
        Map<String, Integer> topicThreadmap = new HashMap<>();
        topicThreadmap.put("WordCount",1);
        JavaPairReceiverInputDStream<String, String> lines = KafkaUtils.createStream(
                javaStreamingContext, "localhost:2181",
                "DefaultConsumerGroup", topicThreadmap
        );
        JavaDStream<String>words=lines.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {
            @Override
            public Iterator<String> call(Tuple2<String, String> line) throws Exception {
                String str[]=line._2.split(" ");
                List<String> list = new ArrayList<>();
                for(String string:str)
                    list.add(string);
                return list.iterator();
            }
        });
        JavaPairDStream<String,Integer>pairs=words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<>(word,1);
            }
        });
        JavaPairDStream<String,Integer>WordCounts=pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });
        WordCounts.print();
        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
        javaStreamingContext.close();
    }
}
