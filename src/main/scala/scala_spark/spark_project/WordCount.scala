package scala_spark.spark_project

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args:Array[String]){
    val conf=new SparkConf()
      .setAppName("WordCount").setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("/home/hdc/word")
    val words = lines.flatMap(line => line.split(" "))
    val pairs = words.map (word => (word, 1))
    val wordCounts = pairs.reduceByKey ((x,y)=>x+y )
    wordCounts.foreach(wordCount => println(wordCount._1 + " appeared " + wordCount._2 + " times."))
  }
}
