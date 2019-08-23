package java_spark.spark_book.第9章.hive;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class HiveDataScore {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("HiveDataScore").setMaster("local");
        SparkSession sparkSession=SparkSession.builder().enableHiveSupport().config(conf).getOrCreate();

        //将学生基本信息导入student_infos表
        //sparkSession.sql能够执行hiveQL语句
        //若已经存在student_infos表则删除
        sparkSession.sql("drop table if exists student_infos");

        //不存在student_infos表则创建
        sparkSession.sql("create table if not exists student_infos(name string,age int) " +
                "row format delimited fields terminated by ' '");
        //将学生信息导入student_infos表中
        sparkSession.sql("load data " +
                "local inpath '/home/hdc/data/user' "+
                "into table student_infos   "
        );
        //将学生成绩信息导入
        sparkSession.sql("drop table if exists stu_score");
        //不存在则创建
        sparkSession.sql("create table if not exists stu_score(name string,score int) " +
                "row format delimited fields terminated by ' '");
        sparkSession.sql(
                "load data local inpath '/home/hdc/data/score' into table stu_score"
        );

        //执行sql查询，查询成绩大于80分的学生
        Dataset<Row> goodStuInfo=sparkSession.sql(
                "select si.name,si.age,ss.score "+
                        "from student_infos si join stu_score ss on si.name=ss.name "+
                        "where ss.score>=80"
        );
        goodStuInfo.show();
        sparkSession.sql("drop table if exists GoodStuTable");
        //然后将dataset数据保存到表中
        goodStuInfo.write().format("json").saveAsTable("GoodStuTable");
        //针对表直接创建dataset
        Dataset<Row>GoodStuDF=sparkSession.table("GoodStuTable");
        GoodStuDF.show();
    }
}
