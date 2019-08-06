package scala_spark.spark_book.第6章

import org.apache.spark.{SparkConf, SparkContext}

//同个节点中的同种类型的多个任务对一个节点上的一个变量进行共享性操作（只读）
//在工作节点work中会有多个executor进程，每个executor运行多个task任务，算子的函数执行由多个task任务组成
//因此函数内使用到的外部变量会拷贝到执行这个函数的每一个task任务中，这样会影响性能
//此时出现共享变量（广播变量的一种），将所需要到的外部变量变成共享变量，这样就会拷贝一份到每个节点里，而不是每一个任务里。

object broadcast_var {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("broadcast_var").setMaster("local")
    val sc=new SparkContext(conf)
    val factor=3
    val factorBroadcast=sc.broadcast(factor)//共享变量
    val arr=Array(1,2,3,4)
    val rdd=sc.parallelize(arr)
    val numRDD=rdd.map(num=>num*factorBroadcast.value)
    numRDD.foreach(num=>println("num:"+num))
  }
}
