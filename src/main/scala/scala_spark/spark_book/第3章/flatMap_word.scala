package scala_spark.spark_book.第3章

import org.apache.spark.{SparkConf, SparkContext}

object flatMap_word {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("flatMap_word").setMaster("local")
    val sc=new SparkContext(conf)
    val inputRDD=sc.textFile("/home/hdc/word")
    val wordsRDD=inputRDD.flatMap(line=>line.split(" "))
    println(wordsRDD.collect().mkString(","))
    println(wordsRDD.getClass.getSimpleName)

  }
}
