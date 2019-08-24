package java_spark.spark_book.第6章;

//map与mapPartition的区别：
//map是对rdd中的每一个元素进行操作
//mapPartition是对rdd中的每一个分区的迭代器进行操作
//mapPatiiton的优点：若是普通的map，一个partition中有1万条数据，那么你的function就需要执行和计算1万次
//使用mapPartiton后，一个task只会执行一次function，function会一次接受所有的partition数据，只需要执行一次
//但是若数据量过大则会造成内存不足，出现OOM异常。

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class map_mapPartition {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("map_mapPartition").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        System.out.println("-----------------map-------------------");
        JavaRDD<Integer> listRDD=sc.parallelize(Arrays.asList(1,2,3,4,5),2);
        JavaRDD<Integer>resultmapRDD=listRDD.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) throws Exception {
                return integer*2;
            }
        });
        resultmapRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println("map:"+integer);
            }
        });
        System.out.println("-----------------mapPartition-------------------");
        JavaRDD<Integer>resMapPartiton=listRDD.mapPartitions(new FlatMapFunction<Iterator<Integer>, Integer>() {
            public Iterator<Integer> call(Iterator<Integer> x) throws Exception {
                List<Integer> res=new ArrayList<Integer>();
                while(x.hasNext()){
                    res.add(x.next()*2);
                }
                return res.iterator();
            }
        });
        resMapPartiton.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println("mapPartiton:"+integer);
            }
        });

        resMapPartiton.foreachPartition(new VoidFunction<Iterator<Integer>>() {
            public void call(Iterator<Integer> x) throws Exception {
                while (x.hasNext()){
                    System.out.println("foreachPartition:"+x.next());
                }
            }
        });
    }
}
