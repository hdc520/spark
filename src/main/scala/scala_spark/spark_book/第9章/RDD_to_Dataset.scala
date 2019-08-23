package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
object RDD_to_Dataset {
  case  class Student(name:String,age:Long)
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("RDD_to_Dataset").setMaster("local");
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()

    import sparkSession.implicits._
    val student=sparkSession.sparkContext.textFile("/home/hdc/data/data2")
      .map(line=> line.split(" "))
      .map(arr=>Student(arr(0),arr(1).toInt))
    println(student.getClass.getSimpleName)

    val stuDF=student.toDF()
    stuDF.createOrReplaceTempView("stu_table")
    val olderDF=sparkSession.sql("select * from stu_table where age>25")
    olderDF.show()
    val olderRDD=olderDF.rdd
    olderRDD.collect().mkString(" ")


  }
}
