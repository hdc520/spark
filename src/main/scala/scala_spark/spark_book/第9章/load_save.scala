package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object load_save {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("load_save").setMaster("local");
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()

    val userDF=sparkSession.read.load("/home/hdc/data/users.parquet")
    userDF.printSchema()
    userDF.show()

    //
  }

}
