package java_spark.spark_book.第4章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class pairRDD {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("pairRDD").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> inputRDD=sc.textFile("/home/hdc/word");
        JavaPairRDD<String,String> pairRdd=inputRDD.mapToPair(new PairFunction<String,String,String>(){
            public Tuple2<String,String> call(String x){
                return new Tuple2<String, String>(x.split(" ")[0],x);
            }
        });
        /*或者
        JavaPairRDD<String,String> pairRdd=inputRDD.mapToPair(
                (PairFunction<String, String, String>) x ->
                        new Tuple2<String, String>(x.split(" ")[0],x)
        );
         */
        System.out.println(pairRdd.collect().toString());

        JavaPairRDD<String,String> lowerPairRDD=pairRdd.filter(new Function<Tuple2<String, String>, Boolean>() {
            public Boolean call(Tuple2<String,String> x){
                return (x._2().length()<10);
            }
        });

        System.out.println(lowerPairRDD.collect().toString());
    }
}
