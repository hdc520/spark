package scala_spark.spark_book.第3章

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

//spark RDD是惰性求值的，而有时我们希望多次同时使用同一个RDD，如果简单地对RDD调用行动操作
//spark每次都会重算RDD以及它的所有依赖，这样消耗过大，此时就可以对RDD进行持久化。

object persistRDD {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("persistRDD").setMaster("local")
    val sc=new SparkContext(conf)
    val inputRDD=sc.parallelize(List(1,2,3,4,5))
    //非持久化
    val resultRDD=inputRDD.map(x=>x*x)
    println(resultRDD.count())
    println(resultRDD.collect().mkString(","))

    //持久化
    resultRDD.persist(StorageLevel.DISK_ONLY)
    println(resultRDD.count())
    println(resultRDD.collect().mkString(","))

  }

}
