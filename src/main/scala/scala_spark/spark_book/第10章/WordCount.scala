package scala_spark.spark_book.第10章

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // nc -lk 9999
    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val sc=new StreamingContext(conf,Seconds(1))
    val lines=sc.socketTextStream("localhost",9999)
    val words=lines.flatMap(x=>x.split(" "))
    val wordpairs=words.map(x=>(x,1))
    val wordcounts=wordpairs.reduceByKey(_+_)
    Thread.sleep(5000)
    wordcounts.print()
    sc.start()
    sc.awaitTermination()
  }
}
