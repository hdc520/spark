package java_spark.spark_book.第9章;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;
public class Sql_read_file {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("Sql_read_file").setMaster("local");
        SparkSession spark= SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> df=spark.read().json("/home/hdc/data/json");
        df.show();
        df.select("name","age").show();
        df.select(col("name"),col("age").plus(1)).show();
        df.filter(col("score").gt(90)).show();
        df.sort(col("score")).show();
        df.groupBy(col("class")).count().show();
    }
}
