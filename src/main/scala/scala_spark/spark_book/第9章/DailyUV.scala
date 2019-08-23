package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
object DailyUV {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("DailyUV").setMaster("local")
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()
    //构造数据访问数据，并创建dataset
    val userAccesLog=Array(
      "2015-10-01,1122",
      "2015-10-01,1121",
      "2015-10-02,1122",
      "2015-10-02,1121",
      "2015-10-03,1124",
      "2015-10-03,1123",
      "2015-10-04,1121",
      "2015-10-02,1122",
      "2015-10-02,1121",
      "2015-10-03,1124",
      "2015-10-03,1123",
      "2015-10-04,1121"
    )
    val userAccessLogRDD=sparkSession.sparkContext.parallelize(userAccesLog)
    //将模拟出来的用户访问日志RDD转换为Dataset：
    //第一步将普通RDD转换元素为Row的RDD
    val userAccessLogRowRDD=userAccessLogRDD.map{log=>
      Row(log.split(",")(0),log.split(",")(1).toInt)
    }
    //构造Dataset的元数据
    val structType= StructType(
      Array(
        StructField("date",StringType,true),
        StructField("id",IntegerType,true)
      )
    )
    //创建Dataset
    val userAccessLogRowDF=sparkSession.createDataFrame(userAccessLogRowRDD,structType)
//    userAccessLogRowDF.groupBy("date").agg('date,countDistinct('id)).map{
//      row=>Row(row(1),row(2))
//    }.collect().foreach(println())
    userAccessLogRowDF.groupBy(col("date"),col("id")).count().collect().foreach(println)
  }
}
