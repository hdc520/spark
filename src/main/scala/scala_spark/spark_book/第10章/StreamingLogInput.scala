package scala_spark.spark_book.第10章

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingLogInput {
  def main(Args:Array[String]):Unit={
    val conf=new SparkConf().setAppName("StreamingLogInput").setMaster("local")
    val ssc=new StreamingContext(conf,Seconds(4));
    val line=ssc.socketTextStream("localhost",9999);
    val errorLines=line.filter(_.contains("M"))
    errorLines.print()
    ssc.start()
    ssc.awaitTermination()
    //nc -lk 9999开启端口写入数据
  }
}
