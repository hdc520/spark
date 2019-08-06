package scala_spark.spark_book.第6章

import org.apache.spark.{SparkConf, SparkContext}


//Accumulator变量主要用于多个节点对一个变量进行共享性操作（累加操作即多个任务对一个变量并行操作的功能
// 但是task只能对Accumulator执行累加操作，不能读取它的值，只有Driver程序可以读取Accumulator的值）

object Accumulator {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("accumulator").setMaster("local")
    val sc=new SparkContext(conf)
    val sumaccumulator=sc.accumulator(0)
    val arr=Array(1,2,3,4,5)
    val inputRDD=sc.parallelize(arr)
    inputRDD.foreach(num=>sumaccumulator+=num)
    println(sumaccumulator.value)
  }
}
