package scala_spark.spark_project

import org.apache.spark.{SparkConf, SparkContext}

object SecondSort {
  class SecondSortKey(val first:Int,val second:Int)extends Ordered[SecondSortKey]with Serializable {
    def compare(that: SecondSortKey): Int = {
      if (this.first-that.first!=0)
        this.first - that.first
      else
        this.second-that.second
    }
  }
  def main(args:Array[String]):Unit={
    val conf=new SparkConf()
      .setAppName("SecondSort").setMaster("local")
    val sc=new SparkContext(conf)
    val lines=sc.textFile("/home/hdc/data/data1")
    val pairRDD=lines.map{line=>
      (new SecondSortKey(line.split(" ")(0).toInt,line.split(" ")(1).toInt),line)
    }
    val sortRDD=pairRDD.sortByKey()
    sortRDD.foreach(sortline=>println(sortline._2))
  }

}
