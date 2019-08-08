package java_spark.spark_project;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import scala.actors.threadpool.Arrays;

import java.util.Iterator;

public class TopNScore {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("TopN").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        JavaRDD<String> inputRDD=sc.textFile("/home/hdc/data/score.txt");
//        System.out.println("!!!!!!!!!!!!!!!!!!");
        JavaPairRDD<String,Integer>scorePairs=inputRDD.mapToPair(
                new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                String [] str=s.split(" ");
//                System.out.println(str[0]+" "+str[1]);
                return new Tuple2<String, Integer>(str[0],Integer.parseInt(str[1]));
            }
        });

        JavaPairRDD<String,Iterable<Integer>>groupRDD=scorePairs.groupByKey();

        JavaPairRDD<String,Iterable<Integer>>scoreTopN=groupRDD.mapToPair(
                new PairFunction<Tuple2<String, Iterable<Integer>>, String, Iterable<Integer>>() {
            public Tuple2<String, Iterable<Integer>> call(Tuple2<String, Iterable<Integer>> s) throws Exception {

                Integer[]list=new Integer[100];
                Integer[]list1=new Integer[3];
                int i=0,j=0;
                String className=s._1;
                Iterator<Integer> scores=s._2.iterator();
                while(scores.hasNext()){
                    int temp=scores.next();
                    list[i]=temp;
                    i+=1;
                }
                int length=0;
                for (i=0;i<list.length;i++) {
                    if(list[i]!=null)
                        length++;
                }
                for(i=0;i<length;i++){
                    for(j=i+1;j<length;j++){
                        if(list[i]<list[j]){
                            int temp=list[i];
                            list[i]=list[j];
                            list[j]=temp;
                        }
                    }
                }
                for (i=0;i<3;i++) {
                    list1[i] = list[i];

                }
                return new Tuple2<String, Iterable<Integer>>(className, Arrays.asList(list1));
            }
        });
        scoreTopN.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
            public void call(Tuple2<String, Iterable<Integer>> s) throws Exception {
                System.out.println(s._1+":");
                Iterator<Integer>list=s._2.iterator();
                while(list.hasNext()){
                    System.out.println(list.next());
                }
                System.out.println("----------------------------------");
            }
        });
    }
}
