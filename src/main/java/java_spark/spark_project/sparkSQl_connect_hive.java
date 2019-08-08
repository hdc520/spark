package java_spark.spark_project;

import org.apache.spark.sql.SparkSession;
import java.text.ParseException;

public class sparkSQl_connect_hive {
    public static void main(String[] args) throws ParseException {
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark Hive Example")
                .master("local[*]")
                //.config("spark.sql.warehouse.dir", "/user/hive/warehouse")
//                .config("hadoop.home.dir", "/user/hive/warehouse")
                .enableHiveSupport()
                .getOrCreate();
        spark.sql("SELECT * FROM users").show();
    }
}