package scala_spark.spark_book.第10章

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TransformBlackList {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("TransformBlackList").setMaster("local[3]")
    val streamContext=new StreamingContext(conf,Seconds(3))
    val blackList=Array(("Tom",true))
    val blackListRDD=streamContext.sparkContext.parallelize(blackList,2)
    val adsClickLogDStream=streamContext.socketTextStream("localhost",9999)
    //2015 hdc
    //2015 Tom
    val userAdsClickLogDStream=adsClickLogDStream.map{
      adsClickLog=>
        (adsClickLog.split(" ")(1),adsClickLog)
    }
    //(hdc,"2015 hdc")
    //(Tom,"2015 hdc")
    val validAdsClickLogDstream=userAdsClickLogDStream.transform{userAdsClickLogRDD=>{
      val joinRDD=userAdsClickLogRDD.leftOuterJoin(blackListRDD)
      joinRDD.foreach(x=>println("x=="+x))
      val filterRDD=joinRDD.filter(tuple=>{
        println("tuple._1="+tuple._1+"  tuple._2._1="+tuple._2._1+"  tuple._2._2="+tuple._2._2)
        println(tuple._2._2.getOrElse(false))
        if(tuple._2._2.getOrElse(false))
          false
        else {
          true
        }
      })
      val validAdsClickLogRDD=filterRDD.map(tuple=>tuple._2._1)
      validAdsClickLogRDD
    }}

    validAdsClickLogDstream.print()

    streamContext.start()
    streamContext.awaitTermination()
  }
}
