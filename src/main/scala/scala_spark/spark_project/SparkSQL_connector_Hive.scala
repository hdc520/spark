package scala_spark.spark_project

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

//spark2.0以上是使用全新的SparkSession接口代替Spark1.6中的SQLcontext和HiveContext
//来实现对数据的加载、转换、处理等工作，并且实现了SQLcontext和HiveContext的所有功能。
//SparkSession 是 Spark SQL 的入口。
//使用 Dataset 或者 Datafram 编写 Spark SQL 应用的时候，第一个要创建的对象就是 SparkSession。
//Builder 是 SparkSession 的构造器。 通过 Builder, 可以添加各种配置。

object SparkSQL_connector_Hive {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("Spark_hiveSql").setMaster("local")
//    val sc=new SparkContext(conf)
    val hiveCtx=SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
    val rows=hiveCtx.sql("SELECT name,age from users")
    println("rows.show()"+rows.show())
    rows.foreach(x=>println("x："+x(0)+" "+x(1)))
    val firstRow=rows.first()
    println(firstRow.get(0)+" "+firstRow.get(1))
  }

}
