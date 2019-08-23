package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object UDF extends Serializable{

  def main(args: Array[String]): Unit = {
    @transient
    val conf=new SparkConf().setAppName("UDF").setMaster("local")
    conf.registerKryoClasses(Array[Class[_]](classOf[Nothing]))
    @transient
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()

    //构造模拟数据
    val name=Array("hdc","Tom","Sam","Tony")
    val nameRDD=sparkSession.sparkContext.parallelize(name,3)
    val nameRowRDD=nameRDD.map(name=>Row(name))
    val structType=StructType(
      Array(
        StructField("name",StringType,true)
      )
    )
    val nameDF=sparkSession.createDataFrame(nameRowRDD,structType)
    //注册为一张表
    nameDF.createOrReplaceTempView("name_table")

    //定义和注册自定义函数
    //参数含义：函数名，函数体（匿名函数自己写），
    sparkSession.udf.register("strLen":String,(str:String)=>return str.length)

    //使用自定义函数
    val res=sparkSession.sql("select name,strLen(name) from name_table").collect()
    println(res.mkString(","))
  }
}
