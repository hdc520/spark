package scala_spark.spark_book.第3章

import org.apache.spark.{SparkConf, SparkContext}

object action_operate_RDD {
  def main(args:Array[String]):Unit={
    val conf=new SparkConf().setAppName("action_operate_RDD").setMaster("local")
    val sc=new SparkContext(conf)

    val inputRDD=sc.textFile("/home/hdc/word")
    val hdcRDD=inputRDD.filter(line=>line.contains("hdc"))
    val amRDD=inputRDD.filter(line=>line.contains("am"))
    val hdc_am_RDD=hdcRDD.union(amRDD)
    //RDD.collect().mkString("")输出RDD的内容
    //collect不能用在大规模数据集上。
    println("hdcRDD：\n"+hdcRDD.collect().mkString("\n"))
    println("amRDD:\n"+amRDD.collect().mkString("\n"))
    println("hdc_am_RDD：\n"+hdc_am_RDD.collect().mkString("\n"))
    //取出一定数量的RDD中元素
    println(hdc_am_RDD.take(2).foreach(println(_)))

  }

}
