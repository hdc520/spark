package scala_spark.spark_book.第5章
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.spark.{SparkConf, SparkContext}
object write_json {
  case class person(firstName:String,LastName:String)
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("write_json").setMaster("local")
    val sc=new SparkContext(conf)
    val inputRDD=sc.textFile("/home/hdc/data/json")
    val mapper=new ObjectMapper()
    val resultRDD=inputRDD.flatMap(record=>{
      try{
        Some(mapper.readValue(record,classOf[person]))
      }catch {
        case e:Exception=>None
      }
    })
    resultRDD.map(mapper.writeValueAsString(_)).saveAsObjectFile("/home/hdc/data/write_json")
  }
}
