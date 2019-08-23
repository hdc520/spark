//package scala_spark.spark_book.第9章
//
//import org.apache.spark.SparkConf
//import org.apache.spark.sql.types.{IntegerType, StringType, StructField}
//import org.apache.spark.sql.{Row, SparkSession, types}
//
//object join_json {
//  def main(args: Array[String]): Unit = {
//    val conf=new SparkConf().setAppName("join_json").setMaster("local")
//    val sparkSession=SparkSession.builder().config(conf).getOrCreate();
//    val stuScoreDF=sparkSession.read.json("/home/hdc/data/stu1_json")
//    stuScoreDF.createOrReplaceTempView("stu_score")
//    val StuGoodScoreDF=sparkSession.sql("select name,score from stu_score where score>80")
//    val stuGoodName=StuGoodScoreDF.rdd.map(name=>name.getString(0)).collect()
//
//    val stuInfoDF=sparkSession.read.json("/home/hdc/data/stu2_json")
//    stuInfoDF.show()
//    stuInfoDF.createOrReplaceTempView("stu_Info")
//    var str_SQL="select name,age from stu_Info where name in ("
//    for(i<- 0 until stuGoodName.length){
//      str_SQL+="'"+stuGoodName(i)+"'"
//      if(i<stuGoodName.length-1)
//        str_SQL+=","
//    }
//    str_SQL+=")"
//    val StuGoodInfoDF=sparkSession.sql(str_SQL)
//    val stuGoodInfo= StuGoodScoreDF.rdd
//      .map(row=>(row.getAs[String]("name"),row.getAs[Long]("score"))).join(
//        StuGoodScoreDF.rdd.map{line=>
//          (line.getAs[String]("name"),line.getAs[Long]("age"))
//        }
//      )
//    val stuGoodInfoRowRDD=stuGoodInfo.map(info=>Row(info._1,info._2._1.toString.toInt
//    ,info._2._2.toString.toInt
//    ))
//
//    val structType=types.StructType(
//      Array(
//        StructField("name",StringType,true) ,
//        StructField("score",IntegerType,true),
//        StructField("age",IntegerType,true)
//      )
//    )
//    val stuDF=sparkSession.createDataFrame(stuGoodInfoRowRDD,structType)
//    stuDF.show()
//
//
//
//
//
//
//
//
//  }
//}
