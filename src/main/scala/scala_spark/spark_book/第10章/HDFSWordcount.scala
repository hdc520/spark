package scala_spark.spark_book.第10章

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object HDFSWordcount {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("HDFSWordcount").setMaster("local[3]")
    val streamContext=new StreamingContext(conf,Seconds(8))

    val lines=streamContext.textFileStream("hdfs://localhost:9000/data")
    val words=lines.flatMap(x=>x.split(" "))
    val wordPairs=words.map(x=>(x,1))
    val wordCounts=wordPairs.reduceByKey(_+_)
    wordCounts.print()
    streamContext.start()
    streamContext.awaitTermination()
  }
}
