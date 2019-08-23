package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object read_Parquet {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("read_Parquet").setMaster("local")
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()

    val userDF=sparkSession.read.parquet("/home/hdc/data/users.parquet")
    userDF.createOrReplaceTempView("user")
    val userNameDF=sparkSession.sql("select name from user")
    val userRDD=userNameDF.rdd.collect().foreach(name=>println("name:"+name))
    println(userNameDF.rdd.collect().getClass.getSimpleName)
    val userName=userNameDF.rdd.map(line=>line.getString(0)).foreach(name=>println("name:"+name))
    println(userNameDF.rdd.map(line=>line.getString(0)).getClass.getSimpleName)

  }
}
