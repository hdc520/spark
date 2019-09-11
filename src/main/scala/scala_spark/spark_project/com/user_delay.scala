package scala_spark.spark_project.com

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object user_delay {
  class userINFO(val userID:String,val locID:String,val time:Long,val delay_time:Int){
    override def toString = s"userINFO($userID, $locID, $time, $delay_time)"
  }

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local[2]").setAppName("user_delay")
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()

  }
}
