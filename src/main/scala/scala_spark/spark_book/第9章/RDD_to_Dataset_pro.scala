package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object RDD_to_Dataset_pro {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("RDD_to_Dataset_pro").setMaster("local");
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()
    //构造出元素为Row的普通的RDD
    val stuRDD=sparkSession.sparkContext.textFile("/home/hdc/data/data2")
      .map(line=>Row(line.split(" ")(0),line.split(" ")(1).toInt))
    //编程方式动态创建元数据信息
    val schema=StructType(Array(
      StructField("name",StringType,true),
      StructField("age",IntegerType,true)
      )
    )
    //正常使用
    val stuDF=sparkSession.createDataFrame(stuRDD,schema)
    stuDF.createOrReplaceTempView("stu_table")
    val resultsDF=sparkSession.sql("select name from stu_table where age>25")
    val resultRDD=resultsDF.rdd.collect().foreach(line=>println(line))
  }
}
