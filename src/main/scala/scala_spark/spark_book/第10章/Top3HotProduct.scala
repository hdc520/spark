package scala_spark.spark_book.第10章

import org.apache.spark.SparkConf
import org.apache.spark.sql.Row
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Top3HotProduct {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("Top3HotProduct").setMaster("local[2]")
    val streamContext=new StreamingContext(conf,Seconds(1))
    val productClickLogsDstream=streamContext.socketTextStream("localhost",9999)
    val productClickPairsDstream=productClickLogsDstream.map{
      x=>(x.split(" ")(2)+"_"+x.split(" ")(1),1)
    }
    val categoryProductCountsDstream=productClickPairsDstream.reduceByKeyAndWindow(
      (v1:Int,v2:Int)=>v1+v2,
      Seconds(60),
      Seconds(10)
    )

    categoryProductCountsDstream.foreachRDD(categoryProductCountsRDD=>{
      val categoryProductCountRowRDD=categoryProductCountsRDD.map(tuple=>{
        val category=tuple._1.split("_")(0)
        val product=tuple._1.split("_")(1)
        val count=tuple._2
        Row(category,product,count)
      })
      val structType=StructType(Array(
        StructField("category",StringType,true),
        StructField("product",StringType,true),
        StructField("count",IntegerType,true)
      ))
      val hiveContext=new HiveContext(categoryProductCountRowRDD.context)
      val categoryProductDF=hiveContext.createDataFrame(categoryProductCountRowRDD,structType)
      categoryProductDF.createOrReplaceTempView("table")
      val top3ProductDF = hiveContext.sql("select category,product,count " + "from (" + "select category,product,count," + "row_number() over (partition by category order by count desc) rank from table ) tmp_view where rank<=3")
      top3ProductDF.show()
    })

    streamContext.start()
    streamContext.awaitTermination()
  }
}
