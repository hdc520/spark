package scala_spark.spark_project

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test").setMaster("local[3]")
    val ss = SparkSession.builder().config(conf).getOrCreate()
    val inputSet=ss.read.csv("hdfs://localhost:9000/myhdfs/input.csv").toDF(
      "userID","locID","time","delay_time"
    )
    inputSet.show()
    val midRDD=inputSet.rdd

  }
}