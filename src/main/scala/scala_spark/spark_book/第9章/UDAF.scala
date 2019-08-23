package scala_spark.spark_book.第9章

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, IntegerType, StringType, StructField, StructType}

object UDAF {
  class StringCount extends UserDefinedAggregateFunction with Serializable {
    //中间进行聚合时，所处理的数据类型
    override def bufferSchema: StructType = {
      StructType(
        Array(StructField("count",IntegerType,true))
      )
    }
    //函数返回值的类型
    override def dataType: DataType = {
      IntegerType
    }
    //输入数据的类型
    override def inputSchema: StructType = {
      StructType(
        Array(StructField("str",StringType,true))
      )
    }

    override def deterministic: Boolean = true

    //为每个分组的数据执行初始化操作
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0)=0

    }

    //每个分组有新的值进来的时候，如何进行分组对应的聚合值计算
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      buffer(0)=buffer.getAs[Int](0)+1
    }

    //因为spark是分布式的，所以一个分组的数据，可能会在不同的节点上进行局部聚合，就是update，
    //merge是合并不同节点上的值
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0)=buffer1.getAs[Int](0)+buffer2.getAs[Int](0)

    }
    //一个分组的聚合值如何通过中间的缓存的聚合值，返回最终的聚合值
    override def evaluate(buffer: Row): Any ={
      buffer.getAs[Int](0)
    }
  }
  def main(args: Array[String]): Unit = {
    @transient
    val conf=new SparkConf().setAppName("UDAF").setMaster("local")
    conf.registerKryoClasses(Array[Class[_]](classOf[Nothing]))
    @transient
    val sparkSession=SparkSession.builder().config(conf).getOrCreate()

    //构造模拟数据
    val name=Array("hdc","Tom","Sam","Tony","hdc","hdc","Tony")
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
    sparkSession.udf.register("strCount":String,new StringCount)

    //使用自定义函数
    val res=sparkSession.sql("select name,strCount(name) from name_table" +
      " group by name").collect().foreach(println)

  }
}
