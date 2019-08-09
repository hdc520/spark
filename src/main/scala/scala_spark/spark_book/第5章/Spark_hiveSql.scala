package scala_spark.spark_book.第5章

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
object Spark_hiveSql {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("Spark_hiveSql").setMaster("local")
    val sc=new SparkContext(conf)
    val hiveCtx=SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
    val rows=hiveCtx.sql("SELECT name,age from users")
    println("rows.show()"+rows.show())
    rows.foreach(x=>println("x："+x(0)+" "+x(1)))
    val firstRow=rows.first()
    println(firstRow.get(0)+" "+firstRow.get(1))
  }

}
