package java_spark.spark_project;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.List;

public class TopN {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("TopN").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String>inputRDD=sc.textFile("/home/hdc/data/topn");
        JavaPairRDD<Integer,String>pairs=inputRDD.mapToPair(new PairFunction<String, Integer, String>() {
            public Tuple2<Integer, String> call(String s) throws Exception {
                return new Tuple2<Integer, String>(Integer.parseInt(s),s);
            }
        });
        JavaPairRDD<Integer,String>sortNum=pairs.sortByKey(false);
        sortNum.foreach(new VoidFunction<Tuple2<Integer, String>>() {
            public void call(Tuple2<Integer, String> s) throws Exception {
                System.out.println("sortByKey:"+s._1+" "+s._2);
            }
        });
        JavaRDD<Integer>resRDD=sortNum.map(new Function<Tuple2<Integer, String>, Integer>() {
            public Integer call(Tuple2<Integer, String> s) throws Exception {
                return s._1;
            }
        });
        List<Integer>list=resRDD.take(3);
        for(int i:list){
            System.out.println("i:"+i);
        }

    }
}
