package java_spark.spark_book.第9章;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;
public class SQL_operation {
    public static void main(String []Args){
        SparkConf conf=new SparkConf().setAppName("SQL_operation").setMaster("local");
        SparkSession sparkSession=SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> df=sparkSession.read().json("hdfs://localhost:9000/data/json");
        //打印dataset中所有的数据。
        df.show();
        //打印dataset的元数据信息
        df.printSchema();
        //查询某列所有的数据
        df.select("name").show();
        //查询列的数据并操作
        df.select(col("name"),col("age").plus(1)).show();
        //对某一列的值进行过滤
        df.filter(col("score").gt(90)).show();
        //分组
        df.groupBy(col("class")).max().show();
    }


}

