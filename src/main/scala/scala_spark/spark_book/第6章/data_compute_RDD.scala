package scala_spark.spark_book.第6章

import org.apache.spark.{SparkConf, SparkContext}

object data_compute_RDD {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("data_compute_RDD").setMaster("local")
    val sc=new SparkContext(conf)

    val dataRDD=sc.parallelize(List(1,2,3,4,-1,6,7,8,9))
    println("RDD中元素个数："+dataRDD.count())
    println("元素的平均值："+dataRDD.mean())
    println("总和："+dataRDD.sum())
    println("最大值："+dataRDD.max())
    println("最小值："+dataRDD.min())
    println("方差："+dataRDD.variance())
    println("标准差："+dataRDD.stdev())

  }


}
