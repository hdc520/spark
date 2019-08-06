package java_spark.spark_project;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//按每个单词出现的次数进行降序排列
public class high_WordCount {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("lineCount").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("/home/hdc/word");
        JavaRDD<String>words=lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                String numword[]=s.split(" ");
                List<String> list=new ArrayList<String>();
                for(String str:numword)
                    list.add(str);
                return list.iterator();
            }
        });

        JavaPairRDD<String,Integer>pairs=words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });

        JavaPairRDD<String,Integer> wordCounts=pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });
        JavaPairRDD<Integer,String>countWords=wordCounts.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            public Tuple2<Integer, String> call(Tuple2<String, Integer> t) throws Exception {
                return new Tuple2<Integer, String>(t._2,t._1);
            }
        });
        JavaPairRDD<Integer,String>sortByKeyRDD=countWords.sortByKey(false);
        JavaPairRDD<String,Integer>LastWordRDD=sortByKeyRDD.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            public Tuple2<String, Integer> call(Tuple2<Integer, String> t) throws Exception {
                return new Tuple2<String, Integer>(t._2,t._1);
            }
        });
        LastWordRDD.foreach(new VoidFunction<Tuple2<String,Integer >>() {
            public void call(Tuple2<String, Integer> w) throws Exception {
                System.out.println(w._1+":"+w._2);
            }
        });
    }
}
