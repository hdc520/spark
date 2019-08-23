package java_spark.spark_book.第10章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class persistWordCount {
    public static void main(String[] args) throws Exception{
        SparkConf conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
        JavaStreamingContext streamingContext=new JavaStreamingContext(conf, Durations.seconds(3));

        streamingContext.checkpoint("hdfs://localhost:9000/data/WC_checkpoint");
        JavaReceiverInputDStream<String> lines=streamingContext.socketTextStream("localhost",9999);
        JavaDStream<String>words=lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                String word[]=s.split(" ");
                List<String> list=new ArrayList<>();
                for(String str:word){
                    list.add(str);
                }
                return list.iterator();
            }
        });

        JavaPairDStream<String,Integer>pairs=words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        });
        JavaPairDStream<String,Integer>WordCounts=pairs.updateStateByKey(
                new Function2<List<Integer>, Optional<Integer>, Optional<Integer>>() {
            @Override
            public Optional<Integer> call(List<Integer> values, Optional<Integer> state) throws Exception {
                Integer newValue=0;
                if(state.isPresent())
                    newValue=state.get();
                for(Integer integer:values)
                    newValue+=integer;
                return Optional.of(newValue);
            }
        });

        //每次得到当前所有单词的统计次数后，将其写入mysql中进行存储，进行持久化，以便后续操作
        WordCounts.foreachRDD(new VoidFunction<JavaPairRDD<String, Integer>>() {
            @Override
            public void call(JavaPairRDD<String, Integer> s) throws Exception {
                s.foreachPartition(new VoidFunction<Iterator<Tuple2<String, Integer>>>() {
                    @Override
                    public void call(Iterator<Tuple2<String, Integer>> wordCouns) throws Exception {
                        //给每个partition获取一个连接
                        Connection connection= ConnectionsPool.getConnection();
                        Tuple2<String,Integer>wordCount=null;
                        while (wordCouns.hasNext()){
                            wordCount=wordCouns.next();
                            String sql="insert into wordcount(word,count)"+
                                    " values('"+wordCount._1+"',"+wordCount._2+")";
                            connection.createStatement().executeUpdate(sql);
                        }
                        //还会一个连接
                        ConnectionsPool.returnConnection(connection);
                    }
                });
            }
        });

        streamingContext.start();
        streamingContext.awaitTermination();
        streamingContext.close();
    }
}
