package scala_spark.spark_book.第10章

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object UpdateStateByKeyWordCount {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("UpdateStateByKeyWordCount").setMaster("local[3]")
    val ssc=new StreamingContext(conf,Seconds(2))
    ssc.checkpoint("hdfs://localhost:9000/data/WC_checkpoint")

    val lines=ssc.socketTextStream("localhost",9999)
    val words=lines.flatMap(x=>x.split(" "))
    val pairs=words.map(x=>(x,1))
    val wordCounts=pairs.updateStateByKey((values:Seq[Int],state:Option[Int])=>{
      var newValue=state.getOrElse(0)
      for(value<-values)
        newValue+=value
      Option(newValue)
    })

    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
