//package java_spark.spark_book.第10章;
//
//import kafka.serializer.StringDecoder;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.function.FlatMapFunction;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaPairInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import org.apache.spark.streaming.kafka.KafkaUtils;
//import scala.Tuple2;
//
//import java.util.*;
//
//public class KafkaDirectWordcount {
//    public static void main(String[] args) {
//        SparkConf conf=new SparkConf().setAppName("KafkaReceiverWordCount").setMaster("local[2]");
//        JavaStreamingContext javaStreamingContext=new JavaStreamingContext(conf, Durations.seconds(3));
//
//        //创建kafka参数
//        Map<String,String>kafkaParams=new HashMap<>();
//        kafkaParams.put("metadata.broker.list","localhost:9092");
//        //创建set，放入所需读取的topic
//        Set<String> topicSet=new HashSet<>();
//        topicSet.add("WordCount");
//
//        JavaPairInputDStream<String,String>lines=KafkaUtils.createDirectStream(
//                javaStreamingContext,
//                String.class,
//                String.class,
//                StringDecoder.class,
//                StringDecoder.class,
//                kafkaParams,
//                topicSet
//        );
//
//        JavaDStream<String>words=lines.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {
//            @Override
//            public Iterator<String> call(Tuple2<String, String> line) throws Exception {
//                String str[]=line._2.split(" ");
//                List<String>word=new ArrayList<>();
//                for (String s:word)
//                    word.add(s);
//                return word.iterator();
//            }
//        });
//        JavaPairDStream<String,Integer>pairs=words.mapToPair(new PairFunction<String, String, Integer>() {
//            @Override
//            public Tuple2<String, Integer> call(String word) throws Exception {
//                return new Tuple2<>(word,1);
//            }
//        });
//        JavaPairDStream<String,Integer>WordCounts=pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer integer, Integer integer2) throws Exception {
//                return integer+integer2;
//            }
//        });
//        WordCounts.print();
//        javaStreamingContext.start();
//        javaStreamingContext.awaitTermination();
//        javaStreamingContext.close();
//    }
//}
