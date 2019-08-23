package java_spark.spark_book.第10章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class Top3HotProduct {
    public static void main(String[] args) throws Exception{
        SparkConf conf=new SparkConf().setAppName("Top3HotProduct").setMaster("local[2]");
        JavaStreamingContext jsc=new JavaStreamingContext(conf, Durations.seconds(2));

        JavaReceiverInputDStream<String> productLogsStream=jsc.socketTextStream("localhost",9999);

        JavaPairDStream<String,Integer>categoryPairsDstream=productLogsStream.mapToPair(
                new PairFunction<String, String, Integer>() {
                    @Override
                    public Tuple2<String, Integer> call(String s) throws Exception {
                        String str[]=s.split(" ");
                        return new Tuple2<>(
                                str[2]+"_"+str[1],
                                1);
                    }
                }
        );
        JavaPairDStream<String,Integer>categoryPairsCountDstream =categoryPairsDstream
                .reduceByKeyAndWindow(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        },Durations.seconds(60),Durations.seconds(10));

        categoryPairsCountDstream.foreachRDD(new VoidFunction<JavaPairRDD<String, Integer>>() {
            @Override
            public void call(JavaPairRDD<String, Integer> categoryProductCount) throws Exception {
                JavaRDD<Row> categoryProductCountRowRDD=categoryProductCount.map(
                        new Function<Tuple2<String, Integer>, Row>() {
                            @Override
                            public Row call(Tuple2<String, Integer> categoryProductCount) throws Exception {
                                String category=categoryProductCount._1.split("_")[0];
                                String product=categoryProductCount._1.split("_")[1];
                                Integer count=categoryProductCount._2;
                                return RowFactory.create(category,product,count);
                            }
                        }
                );
                List<StructField>structFields=new ArrayList<>();
                structFields.add(DataTypes.createStructField("category",DataTypes.StringType,true));
                structFields.add(DataTypes.createStructField("product",DataTypes.StringType,true));
                structFields.add(DataTypes.createStructField("count",DataTypes.StringType,true));
                StructType structType=DataTypes.createStructType(structFields);
                SparkSession ss=new SparkSession(categoryProductCount.context());
                Dataset<Row> categoryProductCountDF=ss.createDataFrame(categoryProductCountRowRDD,structType);
                categoryProductCountDF.createOrReplaceTempView("product_table");
                Dataset<Row> top3ProductDF=ss.sql(
                        "select category,product,count " +
                                "from (" +
                                "select category,product,count," +
                                "row_number() over (partition BY category order by count desc) rank" +
                                " from product_table" +
                                ") tmp_view " +
                                "where rank<=3"
                );
                top3ProductDF.show();
            }
        });
        jsc.start();
        jsc.awaitTermination();
        jsc.close();

    }
}
