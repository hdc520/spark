package scala_spark.spark_book.第6章

import org.apache.spark.{SparkConf, SparkContext}

object accumulatorLine {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("accumulatorLine").setMaster("local")
    val sc=new SparkContext(conf)
    val inputRDD=sc.textFile("/home/hdc/data/file.txt")
    val accum=sc.longAccumulator("later accum")
    val accumRDD=inputRDD.map{line=>
      if(line.equals(""))
        1
      else
        0
    }
    accumRDD.foreach(x=>accum.add(x))
    println(accum.value)
  }
}
