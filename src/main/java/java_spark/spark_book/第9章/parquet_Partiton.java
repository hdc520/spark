package java_spark.spark_book.第9章;

//parquet之数据源自动分区

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class parquet_Partiton {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("load_save").setMaster("local");
        SparkSession sparkSession= SparkSession.builder().config(conf).getOrCreate();

        Dataset<Row> userDF=sparkSession.read()
                .parquet("/home/hdc/data/users/gender=male/country=US/users.parquet");
        //本地路径不行需要用hdfs上的路径
        userDF.printSchema();
        userDF.show();
    }
}
