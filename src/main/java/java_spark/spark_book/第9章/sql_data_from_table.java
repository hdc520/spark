package java_spark.spark_book.第9章;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class sql_data_from_table {
    public static void main(String []args){
        SparkConf conf=new SparkConf().setAppName("sql_data_from_table").setMaster("local");
        SparkSession sparkSession=SparkSession.builder().enableHiveSupport().config(conf).getOrCreate();
        Dataset<Row> df=sparkSession.read().json("/home/hdc/data/json");
        df.show();
        //创建暂时视图
//        df.createOrReplaceTempView("student1");
//        Dataset<Row> sqldf=sparkSession.sql.sql("select max(score),class from student group by class");
//        sqldf.show();
        try{
            df.createGlobalTempView("student");
        }catch ( Exception e){
            e.printStackTrace();
        }
        sparkSession.sql("select name,age from global_temp.student").show();

        df.registerTempTable("student");
        Dataset<Row> out=sparkSession.sql("select name,age,score from student");
        //DataFrame是spark1.3.0版本提出来的，spark1.6.0版本又引入了DateSet的
        // 但是在spark2.0版本中，DataFrame和DataSet合并为DataSet。
        //Dataset变成rdd
        out.rdd();
        out.show();
        sparkSession.sql("select max(age) from student").show();
    }
}
