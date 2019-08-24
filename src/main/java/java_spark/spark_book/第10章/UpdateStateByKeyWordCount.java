package java_spark.spark_book.第10章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.Optional;
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

public class UpdateStateByKeyWordCount {
    public static void main(String[] args) throws Exception{
        SparkConf conf=new SparkConf().setAppName("UpdateStateByKeyWordCount").setMaster("local[2]");
        JavaStreamingContext streamingContext=new JavaStreamingContext(conf, Durations.seconds(3));
        //首先如果要使用updatestatebykey，就必须设置一个checkpoint目录，开启checkpoint机制
        //这样就能把每个key对应的state除了在内存中存在，也要长期保存一份，内存数据丢失时，可以从checkpoint中恢复
        //开启checkpoint机制
        streamingContext.checkpoint("hdfs://localhost:9000/data/WC_checkpoint");
        //然后实现基础的逻辑
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
        //现在要统计全局的每个单词个数，就无法使用之前的那个方法，此时可以使用updateStateByKey
        JavaPairDStream<String,Integer>WordCounts=pairs.updateStateByKey(
                //这里的Optional相当于scala中的样例类，代表一个值的存在状态，可能存在，也可能不存在
                new Function2<List<Integer>, Optional<Integer>, Optional<Integer>>() {
            @Override
            //对于每个单词每次batch计算的时候，都会调用这个函数
            //这里的第一个参数即values相当于这个batch中多个key的新值，例如（hello,1）(hello,1)那么values=（1,1）
            //第二个参数state指的是之前key的状态
            public Optional<Integer> call(List<Integer> values, Optional<Integer> state) throws Exception {
                //先定义一个全局的单词计数
                Integer newValue=0;
                //其次，判断state是否存在，如果不存在，说明一个key第一次出现，若存在，说明这个key之前已经统计过全局的次数了
                if(state.isPresent())
                    newValue=state.get();
                //接着将新出现的值都累加到newvalue上去，就是一个key目前全局的统计
                for(Integer integer:values)
                    newValue+=integer;
                return Optional.of(newValue);
            }
        });
        //到这里，
        WordCounts.print();
        streamingContext.start();
        streamingContext.awaitTermination();
        streamingContext.close();
    }
}
