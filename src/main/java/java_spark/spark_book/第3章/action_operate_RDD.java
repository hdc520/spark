package java_spark.spark_book.第3章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Iterator;
import java.util.List;

public class action_operate_RDD {
    public static void main(String[] args){
        SparkConf conf=new SparkConf().setAppName("action_operate_RDD").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> inputRDD=sc.textFile("/home/hdc/data/word1");
        JavaRDD<String> hdcRDD=inputRDD.filter((Function<String, Boolean>)s->{
            System.out.println("S:"+s);
            return s.contains("hdc");
        });
        JavaRDD<String> amRDD=inputRDD.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("am");
            }
        });

        JavaRDD<String> hdc_am_RDD=hdcRDD.union(amRDD);
        List<String>list=hdc_am_RDD.collect();
        Iterator<String> iterator=list.iterator();
        System.out.println("------------------------------------------------");
        while (iterator.hasNext())
            System.out.println(iterator.next());
        System.out.println("------------------------------------------------");
        System.out.println("hdc_am_RDD："+hdc_am_RDD.collect().toString());
    }
}
