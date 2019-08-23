package java_spark.spark_book.第9章;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class load_save {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("load_save").setMaster("local");
        SparkSession sparkSession= SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> userDF=sparkSession.read().load("/home/hdc/data/users.parquet");
        userDF.printSchema();
        userDF.show();
//        userDF.select("name","favorite_color").write().save("/home/hdc/data/user_1");
        //指定读取的文件,指定写入文件的格式
        Dataset<Row> textJson=sparkSession
                .read().format("json")
                .load("/home/hdc/data/json");
        textJson.write().format("parquet").save("/home/hdc/data/textparquet");

    }
}
