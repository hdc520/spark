package scala_spark.spark_project

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object TopNScore {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
      .setAppName("TopNScore").setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("/home/hdc/data/score.txt")
    val pairs=lines.map(x=>(x.split(" ")(0),x.split(" ")(1)))
    val groupRDD=pairs.groupByKey()
    groupRDD.foreach(x=>println(x._2.getClass.getSimpleName+"  "+x._2))
    val sortRDD=groupRDD.mapValues { y =>
      val list=y.toList
      val b1=new ArrayBuffer[Int]()
      for(i<-0 until(list.length)){
        b1+=list(i).toInt
      }
      for(i<-0 until b1.length-1){
        for(j<- i+1  until b1.length){
          if(b1(i)<b1(j)){
            var temp=b1(i)
            b1(i)=b1(j)
            b1(j)=temp
          }
        }
      }
      b1.take(3)
    }
    sortRDD.foreach(x=>println(x._1+" "+x._2))
  }
}
