package java_spark.spark_book.第5章;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;
public class SparkSQL_read_json {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("SparkSQL_read_json").setMaster("local");
        SparkSession spark= SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> df=spark.read().json("/home/hdc/data/json");
        df.show();
        df.printSchema();
        df.select("age").show();
        df.select("age","firstName").show();
        df.select(col("age").plus(1),col("lastName")).show();
        df.filter(col("age").lt(27)).show();
        System.out.println(df.filter(col("age").lt(27)).getClass().getSimpleName());

    }
}
