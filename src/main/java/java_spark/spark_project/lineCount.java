package java_spark.spark_project;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

public class lineCount {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("lineCount").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("/home/hdc/word",2);
        JavaPairRDD<String, Integer> pairRdd=lines.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });

        JavaPairRDD<String,Integer> resultRDD=pairRdd.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        resultRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> x) throws Exception {
                System.out.println(x._1+" "+x._2);
            }
        });
    }
}
