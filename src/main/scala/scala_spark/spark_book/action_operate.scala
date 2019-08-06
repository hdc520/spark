package scala_spark.spark_book

import org.apache.spark.{SparkConf, SparkContext}

object action_operate {
  def main(args: Array[String]): Unit = {
//    reduce()
//    collect()
//    count()
//    take()
//    saveAsTextFile()
    countByKey()
  }

  //reduce算子
  def reduce():Unit={
    val conf=new SparkConf().setAppName("reduce").setMaster("local")
    val sc=new SparkContext(conf)
    val list=Array(1,2,3,4,5)
    val listRDD=sc.parallelize(list)
    val result=listRDD.reduce(_+_)
    println("累加："+result)
  }

  //collect算子
  def collect():Unit={
    val conf=new SparkConf().setAppName("reduce").setMaster("local")
    val sc=new SparkContext(conf)
    val list=Array(1,2,3,4,5)
    val listRDD=sc.parallelize(list)
    val DoublelistRDD=listRDD.map(_*2)
    val resultRDD=DoublelistRDD.collect()
    for(i<-resultRDD){
      println("i:"+i)
    }
  }

  //count算子
  def count():Unit={
    val conf=new SparkConf().setAppName("reduce").setMaster("local")
    val sc=new SparkContext(conf)
    val list=Array(1,2,3,4,5)
    val listRDD=sc.parallelize(list)
    val num=listRDD.count()
    println("num:"+num)
  }

  //take(n)算子
  def take():Unit={
    val conf=new SparkConf().setAppName("take").setMaster("local")
    val sc=new SparkContext(conf)
    val list=Array(1,2,3,4,5)
    val listRDD=sc.parallelize(list)
    val num=listRDD.take(3)
    num.foreach(x=>println("x:"+x))
  }

  //saveAsTextFile()算子
  def saveAsTextFile():Unit={
    val conf=new SparkConf().setAppName("saveAsTextFile").setMaster("local")
    val sc=new SparkContext(conf)
    val list=Array(1,2,3,4,5)
    val listRDD=sc.parallelize(list)
    val DoublelistRDD=listRDD.map(_*2)
    DoublelistRDD.saveAsTextFile("/home/hdc/data")
  }
  //countByKey算子
  def countByKey():Unit={
    val conf=new SparkConf().setAppName("countByKey").setMaster("local")
    val sc=new SparkContext(conf)
    val scorelist=Array(
      Tuple2("class1","hdc"),Tuple2("class2","Tom"),Tuple2("class1","Sam"),new Tuple2("class2","Mary")
    )
    val inputRDD=sc.parallelize(scorelist)
    val totalRDD=inputRDD.countByKey();
    totalRDD.foreach(x=>println("班级："+x._1+"人数："+x._2))
  }

  //foreach是从远程集群上操作的，故性能高一点。
}
