package scala_spark.spark_project

import org.apache.spark.{SparkConf, SparkContext}

object high_WordCount {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("high_WordCount").setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("/home/hdc/word")
    val inputRDD=lines.flatMap(line=>line.split(" "))
    val pairRdd=inputRDD.map(word=>(word,1))
    val reduceRDD=pairRdd.reduceByKey(_+_)
    val CountWordRDD=reduceRDD.map(x=>(x._2,x._1))
    val sortRDD=CountWordRDD.sortByKey()
    val LastwordRDD=sortRDD.map(x=>(x._2,x._1))
    LastwordRDD.foreach(wordCount => println(wordCount._1 + ":" + wordCount._2 ))
  }
}
