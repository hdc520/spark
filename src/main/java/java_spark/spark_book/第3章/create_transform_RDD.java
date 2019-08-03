package java_spark.spark_book.第3章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.ArrayList;
import java.util.List;

public class create_transform_RDD {
    public static void main(String args[]){
        SparkConf conf=new SparkConf().setAppName("create_transform_RDD");
        conf.setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        List<String> list= new ArrayList<String>();
        for(int i=0;i<10;i++){
            list.add("num:"+i);
        }

        //从集合中创建初始RDD方法
        //1.利用parallelize方法
        JavaRDD<String> parallelizeRDD=sc.parallelize(list);
        System.out.println(parallelizeRDD.partitions().size());

        //2.利用makeRDD,但是只有scala中才有这种方法。

        //从文件中读取数据作为RDD
        JavaRDD<String> textRDD=sc.textFile("/home/hdc/word");
        System.out.println(textRDD.partitions().size());

        //对RDD的转化操作即将旧的RDD转化成新的RDD（转化操作）
        // 转化出来的RDD是惰性的求值的，只有在行动操作中用到这些RDD才会被计算。
        JavaRDD hdcRDD=textRDD.filter(new Function<String, Boolean>() {
            public Boolean call(String s) throws Exception {
                return s.contains("hdc");
            }
        });

        System.out.println("hdcRDD："+hdcRDD);
    }


}
