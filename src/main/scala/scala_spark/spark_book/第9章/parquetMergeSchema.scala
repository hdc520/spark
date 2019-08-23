package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
object parquetMergeSchema {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("parquetMergeSchema").setMaster("local")
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()

    import sparkSession.implicits._
    //创建一个Dataset作为学生的基本信息，并写入一个parquet中
    val stuNameAge=Array(("hdc",24),("Tom",27))
    val stuNameAgeDF=sparkSession.sparkContext
      .parallelize(stuNameAge,2).toDF("name","age")
    //创建第二个Dataset作为学生成绩信息写入一个parquet文件中

  }
}
