package java_spark.spark_book.第3章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class action_operate_RDD {
    public static void main(String[] args){
        SparkConf conf=new SparkConf().setAppName("action_operate_RDD").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> inputRDD=sc.textFile("/home/hdc/word");
        JavaRDD<String> hdcRDD=inputRDD.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                System.out.println("S:"+s);
                return s.contains("hdc");
            }
        });
        JavaRDD<String> amRDD=inputRDD.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("am");
            }
        });

        JavaRDD<String> hdc_am_RDD=hdcRDD.union(amRDD);
        System.out.println("hdc_am_RDD："+hdc_am_RDD.collect().toString());
    }
}
