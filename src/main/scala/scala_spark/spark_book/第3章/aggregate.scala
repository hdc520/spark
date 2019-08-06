package scala_spark.spark_book.第3章

import org.apache.spark.{SparkConf, SparkContext}

object aggregate {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("aggregate").setMaster("local")
    val sc=new SparkContext(conf)

    val inputRDD=sc.makeRDD(Array(1,2,3,4))
    val avgRDD=inputRDD.aggregate((0,0))(
      (acc,value)=>{//acc指的是初始化的值（单一值和元组都行）value指的是需要处理的RDD
        println("acc._1:"+acc._1+" value:"+value+" acc._2:"+acc._2)
        (acc._1+value,acc._2+1)

      },
      (acc1,acc2)=>{//acc1指的是初始化的值（单一值和元组都行）acc2指的是上个函数处理的结果
        println("acc1._1:"+acc1._1+" acc2._1:"+acc2._1+" acc1._2:"+acc1._2+" acc2._2:"+acc2._2)
        (acc1._1+acc2._1,acc1._2+acc2._2)
      }
    )
    val avg=avgRDD._1/avgRDD._2.toDouble
    println("avg:"+avg)
  }
}
