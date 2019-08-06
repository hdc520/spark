package scala_spark.spark_book.第4章

import org.apache.spark.{SparkConf, SparkContext}

object CombineByKey {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("CombineByKey").setMaster("local")
    val sc=new SparkContext(conf)

    val startData=Seq("coffee"->1,"coffee"->2,"panda"->3,"panda"->9,"coffee"->2)
    val inputRDD=sc.parallelize(startData)

  }
}
