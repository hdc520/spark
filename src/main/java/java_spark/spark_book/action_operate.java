package java_spark.spark_book;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import java.util.Arrays;
import java.util.List;

public class action_operate {
    public static void main(String[] args) {
//        reduce();
//        collect();
//        count();

//        saveAsTextFile();
//        countByKey();
    }

    public static void reduce(){
        SparkConf conf=new SparkConf().setAppName("reduce").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<Integer> list= Arrays.asList(1,2,3,4,5);
        JavaRDD<Integer> listRDD=sc.parallelize(list);

        int result=listRDD.reduce(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });
        System.out.println("累加："+result);
    }

    //collect算子
    //不建议使用此collect操作，因为若数据量过大，要走大量的远程网络传输，，将数据拉取到本地。
    public static void collect(){
        SparkConf conf=new SparkConf().setAppName("collect").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<Integer>list=Arrays.asList(1,2,3,4,5,6);
        JavaRDD<Integer>listRDD=sc.parallelize(list);
        JavaRDD<Integer>DoubleListRDD=listRDD.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) throws Exception {
                return integer*2;
            }
        });
        List<Integer> resultRDD=DoubleListRDD.collect();
        for(int i:resultRDD){
            System.out.print("i "+i+" ");
        }
    }

    //count算子
    public static void count(){
        SparkConf conf=new SparkConf().setAppName("count").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<Integer>list=Arrays.asList(1,2,3,4,5,6);
        JavaRDD<Integer>listRDD=sc.parallelize(list);
        long num=listRDD.count();
        System.out.println("num:"+num);
    }

    //take(n)跟collect算子一样，但是只取前三个
    public static void take(){
        SparkConf conf=new SparkConf().setAppName("take").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<Integer>list=Arrays.asList(1,2,3,4,5,6,7);
        JavaRDD<Integer>listRDD=sc.parallelize(list);
        List<Integer>TopN=listRDD.take(3);
        for(int i:TopN){
            System.out.println("i:"+i);
        }
    }

    //saveAsTextFile算子
    public static void saveAsTextFile(){
        SparkConf conf=new SparkConf().setAppName("saveAsTextFile").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<Integer>list=Arrays.asList(1,2,3,4,5,6,7);
        JavaRDD<Integer>listRDD=sc.parallelize(list);
        JavaRDD<Integer>DoubleListRDD=listRDD.map(new Function<Integer, Integer>() {
            public Integer call(Integer x) throws Exception {
                return x*2;
            }
        });
       DoubleListRDD.saveAsTextFile("/home/hdc/data");
    }

    //countByKey算子按key的值进行统计,返回类型是map()
//    public static void countByKey(){
//        SparkConf conf=new SparkConf().setAppName("countByKey").setMaster("local");
//        JavaSparkContext sc=new JavaSparkContext(conf);
//        List<Tuple2<String,String>> scoreslist=Arrays.asList(
//                new Tuple2<String, String>("class1","hdc"),
//                new Tuple2<String, String>("class2","Tom"),
//                new Tuple2<String, String>("class1","Sam"),
//                new Tuple2<String, String>("class2","Mary")
//        );
//        JavaPairRDD<String,String> inputRDD=sc.parallelizePairs(scoreslist);
//        Map<String,Long> stuCount=inputRDD.countByKey();
//        for(Map.Entry<String,Long> stu:stuCount.entrySet()){
//            System.out.println("班级："+stu.getKey()+"人数："+stu.getValue());
//        }
//    }
}
