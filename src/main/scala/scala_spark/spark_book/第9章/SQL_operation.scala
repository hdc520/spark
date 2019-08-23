package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SQL_operation {
  def main(Args:Array[String]):Unit={
    val conf=new SparkConf().setAppName("SQL_read_json").setMaster("local")
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()
    val df=sparkSession.read.json("hdfs://localhost:9000/data/json")
    println(df.getClass.getSimpleName)
    df.show();
    df.printSchema()
    df.select("score").show()
    df.select(col = "name").show()
    df.select("name","score"+1).show()
    df.filter(df("score")>90).show()
    df.groupBy("class").max().show()

  }
}

