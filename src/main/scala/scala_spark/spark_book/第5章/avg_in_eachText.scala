package scala_spark.spark_book.第5章

import org.apache.spark.{SparkConf, SparkContext}

object avg_in_eachText {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("avg_in_eachText").setMaster("local")
    val sc=new SparkContext(conf)
    //wholeTextFiles返回一个pairRDD，其中键是文件的文件名
    val inputRDD=sc.wholeTextFiles("/home/hdc/data/data.txt")
    val result=inputRDD.mapValues{y=>
      val nums=y.split(" ").map(x=>x.toDouble)
      nums.sum/nums.size.toDouble
    }
    println("result:"+result.collect().mkString)
  }
}
