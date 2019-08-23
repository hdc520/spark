//package java_spark.spark_book.第10章;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import scala.Tuple2;
//
//public class TransformBlackList {
//    public static void main(String[] args) {
//        SparkConf conf=new SparkConf().setAppName("TransformBlackList").setMaster("local[2]");
//        JavaStreamingContext javaStreamingContext=new JavaStreamingContext(conf, Durations.seconds(3));
//
////        //模拟一份黑名单格式
////        List<Tuple2<String,Boolean>> blackList=new ArrayList<>();
////        blackList.add(new Tuple2<>("Tom",true));
//       final JavaPairRDD<String,Boolean> blackListRDD=null;
//        //日志格式date userName
//        JavaReceiverInputDStream<String>addsClickLog=javaStreamingContext
//                .socketTextStream("localhost",9999);
//        //对输入的数据进行转换操作（userID，date userName）
//        JavaPairDStream<String,String>userAdsClickLogStream=addsClickLog.mapToPair(new PairFunction<String, String, String>() {
//            @Override
//            public Tuple2<String, String> call(String adsClickLog) throws Exception {
//                return new Tuple2<>(adsClickLog.split(" ")[1],adsClickLog);
//            }
//        });
//        //将每个batch的RDD与黑名单RDD进行map等算子操作
//        JavaDStream<String>validAdsClickLogDstream=userAdsClickLogStream.transform(
//                new Function<JavaPairRDD<String, String>, JavaRDD<String>>() {
//                    @Override
//                    public JavaRDD<String> call(JavaPairRDD<String, String> s) throws Exception {
////                        userAdsClickLogStream.leftOuterJoin(blackListRDD);
//                        return null;
//                    }
//                }
//        );
//
//
//
//        javaStreamingContext.start();
//        javaStreamingContext.awaitTermination();
//        javaStreamingContext.close();
//    }
//}
