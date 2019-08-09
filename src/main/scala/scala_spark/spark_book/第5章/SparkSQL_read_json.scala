package scala_spark.spark_book.第5章

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkSQL_read_json {
  def main(args:Array[String]):Unit={
    val conf=new SparkConf().setAppName("SparkSQL_read_json").setMaster("local")
    val HiveCtx=SparkSession.builder().config(conf).getOrCreate()
    val jsonRDD=HiveCtx.read.json("/home/hdc/data/json")
    jsonRDD.printSchema()
    jsonRDD.select("firstName","age").show()
  }
}
