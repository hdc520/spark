package java_spark.spark_book.第9章;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;
public class read_Parquet {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("read_Parquet").setMaster("local");
        SparkSession sparkSession= SparkSession.builder().config(conf).getOrCreate();

        Dataset<Row> userDF=sparkSession.read().parquet("/home/hdc/data/users.parquet");
        //将Dataset注册成数据表
        userDF.createOrReplaceTempView("user");
        Dataset<Row> userNameDF=sparkSession.sql("select name from user");
        userNameDF.show();
        //将Dataset转化成一般格式
        List<String> userNameList=userNameDF.javaRDD().map(new Function<Row, String>() {
            public String call(Row row) throws Exception{
                return row.getString(0);
                //row.getAs(0)
            }
        }).collect();
        for(String name:userNameList){
            System.out.println("name:"+name);
        }
    }
}
