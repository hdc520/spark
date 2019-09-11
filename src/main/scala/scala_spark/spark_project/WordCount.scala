package scala_spark.spark_project

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args:Array[String]){
    val conf=new SparkConf()
      .setAppName("WordCount").setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("hdfs://localhost:9000/data/word")
    println(lines.take(1).mkString("").getClass.getSimpleName)
    val words = lines.flatMap(line => line.split(" "))
    val pairs = words.map (word => (word, 1))
    /*
     *val wordRDD=lines.map(line=>line.split(" "))
     *val wordPairs=wordsRDD.map(words=>(words(0),1))
     */
    val wordCounts = pairs.reduceByKey ((x,y)=>x+y )
    wordCounts.foreach(wordCount => println(wordCount._1 + " appeared " + wordCount._2 + " times."))
  }
}
