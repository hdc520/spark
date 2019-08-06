package scala_spark.spark_project

import org.apache.spark.{SparkConf, SparkContext}

object lineCOunt {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("lineCount").setMaster("local")
    val sc=new SparkContext(conf)

    val inputRDD=sc.textFile("/home/hdc/word")
    val pairRdd=inputRDD.map(x=>(x,1))
    val result=pairRdd.reduceByKey(_+_)
    result.foreach(x=>println(x._1+" "+x._2))
  }
}
