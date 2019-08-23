package java_spark.spark_book.第10章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.List;

public class WindowHotWord {
    public static void main(String[] args) throws Exception{
        SparkConf conf=new SparkConf().setAppName("WindowHotWord").setMaster("local[2]");
        JavaStreamingContext jsc=new JavaStreamingContext(conf, Durations.seconds(2));

        //搜索日志格式：
        //leo hello
        //tom world
        JavaReceiverInputDStream<String>searchLog=jsc.socketTextStream("localhost",9999);
        //将搜索日志转换为搜索词
        JavaDStream<String> searchWordDstream=searchLog.map(new Function<String, String>() {
            @Override
            public String call(String s) throws Exception {
                return s.split(" ")[1];
            }
        });

        //将搜索词映射成（word,1）
        JavaPairDStream<String,Integer>searchWordsPair=searchWordDstream.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        });
        //针对搜索词（word,1）的Dstream执行reducebykey操作
        JavaPairDStream<String,Integer>searchWordCountsPair=searchWordsPair
                .reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return null;
            }
        },Durations.seconds(20),Durations.seconds(4));//窗口长度40秒，滑动间隔10秒
        //因为一个batch的时间间隔是5秒，整个窗口的大小为60秒，故每个窗口有60/5=12个RDD。
        //执行transform操作，因为一个窗口就是一个60秒的数据，会变成一个RDD，然后对这一个RDD
        //根据每个搜索词出现的频率进行排序，然后获取排名前三的单词
        JavaPairDStream<String,Integer>finalDstream=searchWordCountsPair.transformToPair(
                new Function<JavaPairRDD<String, Integer>, JavaPairRDD<String, Integer>>() {
                    @Override
                    public JavaPairRDD<String, Integer> call(JavaPairRDD<String, Integer> searchCountsRDD) throws Exception {
                        //执行搜索词与频率的反转
                        JavaPairRDD<Integer,String>countSearchRDD=searchCountsRDD
                                .mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
                                    @Override
                                    public Tuple2<Integer, String> call(Tuple2<String, Integer> wordPair) throws Exception {
                                        return new Tuple2<>(wordPair._2,wordPair._1);
                                    }
                                });
                        JavaPairRDD<Integer,String>sortRDD=countSearchRDD.sortByKey(false);

                        //再次执行反转操作
                        JavaPairRDD<String,Integer>finalSortRDD=sortRDD.mapToPair(
                                new PairFunction<Tuple2<Integer, String>, String, Integer>() {
                                    @Override
                                    public Tuple2<String, Integer> call(Tuple2<Integer, String> s) throws Exception {
                                        return new Tuple2<>(s._2,s._1);
                                    }
                                }
                        );
                        //然后使用take取前三
                        List<Tuple2<String,Integer>>hotWord=finalSortRDD.take(3);
                        for(Tuple2<String,Integer> t:hotWord){
                            System.out.println(t._1+"   "+t._2);
                        }
                        return searchCountsRDD;
                    }
                }
        );

        finalDstream.print();
        jsc.start();
        jsc.awaitTermination();
        jsc.close();
    }
}
