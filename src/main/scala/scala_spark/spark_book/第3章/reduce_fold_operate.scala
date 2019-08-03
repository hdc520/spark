package scala_spark.spark_book.第3章

import org.apache.spark.{SparkConf, SparkContext}

object reduce_fold_operate {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("reduce_fold_operate").setMaster("local")
    val sc=new SparkContext(conf)

    val inputRDD=sc.parallelize(Array(1,2,3,4,5,6,7,8,9,10))
    println(inputRDD.getClass.getSimpleName)
    val reduceRDD=inputRDD.reduce(_+_)
    //val reduceRDD=inputRDD.reduce((x,y)=>x+y)
    println(reduceRDD.getClass.getSimpleName)
    println("reduceRDD:"+reduceRDD)

    val foldRDD=inputRDD.fold(0)(_+_)
    println("foldRDD:"+foldRDD.getClass.getSimpleName)
    println("foldRDD:"+foldRDD)
  }
}
