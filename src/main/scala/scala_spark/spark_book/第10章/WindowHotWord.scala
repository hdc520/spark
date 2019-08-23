package scala_spark.spark_book.第10章

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WindowHotWord {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("WindowHotWord").setMaster("local[3]")
    val streamContext=new StreamingContext(conf,Seconds(2))

    val searchLogsDstream=streamContext.socketTextStream("localhost",9999);
    val searchWordDstream=searchLogsDstream.map(x=>x.split(" ")(1))
    val searchPairsDstream=searchWordDstream.map(x=>(x,1))

    val searchCountsDstream=searchPairsDstream.reduceByKeyAndWindow(
      (v1:Int,v2:Int)=>v1+v2,
      Seconds(20),
      Seconds(4)
    )
    val finalDstream=searchCountsDstream.transform(searchCountsRDD=>{
      val countSearchWordRDD=searchCountsRDD.map(x=>(x._2,x._1))
      val sortRDD=countSearchWordRDD.sortByKey(false)
      val sortCountRDD=sortRDD.map(x=>(x._2,x._1))
      val top3=sortCountRDD.take(3)
      for(t<-top3){
        println(t._1+" : "+t._2)
      }
      searchCountsRDD
    })


    finalDstream.print()
    streamContext.start()
    streamContext.awaitTermination()
  }
}
