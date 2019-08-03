package scala_spark.spark_book.第3章

import org.apache.spark.{SparkConf, SparkContext}

object squard_func {
  def main(args:Array[String]):Unit={
    val conf=new SparkConf().setAppName("squard_func").setMaster("local")
    val sc=new SparkContext(conf)

    val inputRDD=sc.parallelize(List(1,2,3,4))
    val resRDD1=inputRDD.map(x=>x*x)
    println(resRDD1.collect().mkString(","))

  }

}
