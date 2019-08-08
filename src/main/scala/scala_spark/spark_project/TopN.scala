package scala_spark.spark_project

import org.apache.spark.{SparkConf, SparkContext}

object TopN {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("SecondSort").setMaster("local")
    val sc=new SparkContext(conf)
    val inputRDD=sc.textFile("/home/hdc/data/topn")
    val numPairs=inputRDD.map(x=>(x.toInt,x))
    val sortPairs=numPairs.sortByKey(false)
    sortPairs.foreach(x=>println(x._1+" "+x._2))
    val sortRDD=sortPairs.map(x=>x._2).take(3)
    sortRDD.foreach(x=>println("x:"+x))
  }
}
