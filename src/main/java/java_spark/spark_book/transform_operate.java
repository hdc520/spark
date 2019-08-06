package java_spark.spark_book;

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

public class transform_operate {
    public static void main(String[] args) {
//        map();
//        filter();
//        flatmap();
//        groupByKey();
//        reduceByKey();
//        sortByKey();
//        join_cogroup();
    }

    //map算子
    //对任何类型的RDD都可以调用
    public static void map(){
        SparkConf conf=new SparkConf().setAppName("map").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<Integer> list= Arrays.asList(1,2,3,4,5);
        JavaRDD<Integer> listRDD=sc.parallelize(list);
        JavaRDD<Integer> resultRDD=listRDD.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) throws Exception {
                return integer*2;
            }
        });
        resultRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println("map："+integer);
            }
        });
    }

    //filter算子
    public static void filter(){
        SparkConf conf=new SparkConf().setAppName("filter").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        JavaRDD<Integer> inputRDD=sc.parallelize(list);
        JavaRDD<Integer> resultRDD=inputRDD.filter(new Function<Integer, Boolean>() {
            public Boolean call(Integer integer) throws Exception {
                return integer%2==0;
            }
        });

        resultRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println("filter："+integer);
            }
        });
    }

    //faltMap拆分单词
    public static void flatmap(){
        SparkConf conf=new SparkConf().setAppName("flatmap").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> inputRDD=sc.textFile("/home/hdc/word");
        JavaRDD<String> wordRDD=inputRDD.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                String []words=s.split(" ");
                List<String> list=new ArrayList<String>();
                for(String str:words)
                    list.add(str);
                return list.iterator();
            }
        });
        wordRDD.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println("flatmap："+s);
            }
        });
    }

    //groupByKey算子返回还是JavaPairRDD类型
    //但是JavaPairRDD第一个泛型类型不变，第二个变为Iterable类型，即按照key进行分组。
    /*public static void groupByKey(){
        SparkConf conf=new SparkConf().setAppName("groupByKey").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        List<Tuple2<String,Integer>> scoreslist=Arrays.asList(
                new Tuple2<String, Integer>("class1",91),
                new Tuple2<String, Integer>("class2",82),
                new Tuple2<String, Integer>("class1",71),
                new Tuple2<String, Integer>("class2",62)
        );
        JavaPairRDD<String,Integer>inputRDD=sc.parallelizePairs(scoreslist);
        JavaPairRDD<String,Iterable<Integer>>groupRDD=inputRDD.groupByKey();
        groupRDD.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
            public void call(Tuple2<String, Iterable<Integer>> x) throws Exception {
                System.out.println("班级："+x._1);
                Iterator<Integer>it=x._2.iterator();
                while(it.hasNext()){
                    System.out.println("成绩："+it.next());
                }
                System.out.println("-------------------------------");
            }
        });

    }*/

    //reduceByKey算子
    /*public static void reduceByKey(){
        SparkConf conf=new SparkConf().setAppName("reduceByKey").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        List<Tuple2<String,Integer>> scoreslist=Arrays.asList(
                new Tuple2<String, Integer>("class1",91),
                new Tuple2<String, Integer>("class2",82),
                new Tuple2<String, Integer>("class1",71),
                new Tuple2<String, Integer>("class2",62)
        );
        JavaPairRDD<String,Integer> inputRDD=sc.parallelizePairs(scoreslist);
        JavaPairRDD<String,Integer>totalRDD=inputRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        totalRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> x) throws Exception {
                System.out.println("班级："+x._1+" "+x._2);
            }
        });

    }*/

    //sortByKey算子默认为升序，false为降序
//    public static void sortByKey(){
//        SparkConf conf=new SparkConf().setAppName("sortByKey").setMaster("local");
//        JavaSparkContext sc=new JavaSparkContext(conf);
//        List<Tuple2<Integer,String>>scorelist=Arrays.asList(
//                new Tuple2<Integer, String>(100,"hdc"),
//                new Tuple2<Integer, String>(89,"lisi"),
//                new Tuple2<Integer, String>(92,"wagwu"),
//                new Tuple2<Integer,String>(91,"Tom")
//        );
//        JavaPairRDD<Integer,String>inputRDD=sc.parallelizePairs(scorelist);
//        JavaPairRDD<Integer,String>scoresort=inputRDD.sortByKey();
//        scoresort.foreach(new VoidFunction<Tuple2<Integer, String>>() {
//            public void call(Tuple2<Integer, String> x) throws Exception {
//                System.out.println("姓名："+x._2()+" 成绩："+x._1());
//            }
//        });
//    }

    //join和cogroup算子结合操作
    /*public static void join_cogroup(){
        SparkConf conf=new SparkConf().setAppName("join_cogroup").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        List<Tuple2<Integer,String>>studentLis=Arrays.asList(
                new Tuple2<Integer, String>(1,"hdc"),
                new Tuple2<Integer, String>(2,"Sam"),
                new Tuple2<Integer, String>(3,"Tom"),
                new Tuple2<Integer, String>(4,"Tony"),
                new Tuple2<Integer, String>(5,"lisi"),
                new Tuple2<Integer, String>(6,"Mary")
        );
        List<Tuple2<Integer,Integer>>scoreLis=Arrays.asList(
                new Tuple2<Integer, Integer>(1,99),
                new Tuple2<Integer, Integer>(2,89),
                new Tuple2<Integer, Integer>(3,94),
                new Tuple2<Integer, Integer>(4,88),
                new Tuple2<Integer, Integer>(5,90),
                new Tuple2<Integer, Integer>(6,79),
                new Tuple2<Integer, Integer>(3,78),
                new Tuple2<Integer, Integer>(4,56),
                new Tuple2<Integer, Integer>(5,86),
                new Tuple2<Integer, Integer>(6,77)
        );

        //并行化两个RDD
        JavaPairRDD<Integer,String>studentRDD=sc.parallelizePairs(studentLis);
        JavaPairRDD<Integer,Integer>scoreRDD=sc.parallelizePairs(scoreLis);

        //使用join算子关联两个RDD
        JavaPairRDD<Integer,Tuple2<String,Integer>>stu_join_score=studentRDD.join(scoreRDD);
        stu_join_score.foreach(new VoidFunction<Tuple2<Integer, Tuple2<String, Integer>>>() {
            public void call(Tuple2<Integer, Tuple2<String, Integer>> x) throws Exception {
                System.out.println("ID:"+x._1+" 姓名："+x._2._1+" 成绩："+x._2._2);
            }
        });
        //使用cogroup算子
        JavaPairRDD<Integer,Tuple2<Iterable<String>,Iterable<Integer>>>stu_cogroup_score=studentRDD.cogroup(scoreRDD);
        stu_cogroup_score.foreach(new VoidFunction<Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>>>() {
            public void call(Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> x) throws Exception {
                System.out.println("ID:"+x._1+" 姓名："+x._2._1+" 成绩："+x._2._2);
            }
        });


    }*/

}
