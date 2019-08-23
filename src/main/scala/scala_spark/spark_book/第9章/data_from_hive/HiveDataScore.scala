package scala_spark.spark_book.第9章.data_from_hive

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object HiveDataScore {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("HiveDataScore").setMaster("local")
    val sparkSession=SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()

    sparkSession.sql("drop table if exists student_infos")
    sparkSession.sql("create table if not exists student_infos(name string,age int) " +
      " row format delimited fields terminated by ' '")

    sparkSession.sql("load data local inpath '/home/hdc/data/user' into table student_infos")
    sparkSession.sql("drop table if exists stu_score")
    sparkSession.sql("create table if not exists stu_score(name string,score int) " +
      " row format delimited fields  terminated by ' '")
    sparkSession.sql("load data local inpath '/home/hdc/data/score' into table stu_score")
    val goodStuInfo=sparkSession.sql("select si.name,si.age,ss.score from student_infos si" +
      " join stu_score ss on si.name=ss.name where ss.score>80")
    goodStuInfo.show()
    sparkSession.sql("drop table if exists goodStuInfo")
    goodStuInfo.write.saveAsTable("goodStuInfo")
    val goodStuRow=sparkSession.table("goodStuInfo").collect()
    println(goodStuRow.getClass.getSimpleName)
    for(g<-goodStuRow){
      println("row："+g)
    }
  }
}
