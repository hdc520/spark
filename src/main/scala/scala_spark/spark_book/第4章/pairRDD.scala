package scala_spark.spark_book.第4章

import org.apache.spark.{SparkConf, SparkContext}

object pairRDD {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("pairRDD").setMaster("local")
    val sc=new SparkContext(conf)

    val inputRDD=sc.textFile("/home/hdc/word")
    val pairRdd=inputRDD.map(line=>(line.split(" ")(0),line))
    val filterRDD1=pairRdd.filter(_._2.length>10)
    println(filterRDD1.collect().mkString(","))
    val filterRDD2=pairRdd.filter{case (key,value)=>value.length>10}
    println(filterRDD2.collect().mkString(","))
  }
}
