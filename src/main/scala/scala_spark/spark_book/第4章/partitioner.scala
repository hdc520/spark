package scala_spark.spark_book.第4章

import org.apache
import org.apache.spark.{SparkConf, SparkContext}

object partitioner {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("partitioner").setMaster("local")
    val sc=new SparkContext(conf)
    val pairs=sc.parallelize(List((1,2),(2,2),(3,3),(4,4)))
    println("未分区："+pairs.partitioner)
    val partitionerPairs=pairs.partitionBy(new apache.spark.HashPartitioner(2))
    //若后续操作确实要用到partitionerPairs这个RDD时应该将此持久化。
    println("分区："+partitionerPairs.partitioner)
    println("分区："+partitionerPairs.partitioner.get.getPartition(4))
  }
}
