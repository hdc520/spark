package scala_spark.spark_book.第5章

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
object read_csv {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("read_csv").setMaster("local")
    val sc=new SparkContext(conf)
    val hiveCtx=new HiveContext(sc)
//    val data=sc.parallelize(List(("panda",3),("key",6),("hdc",2)))
//    data.saveAsSequenceFile("/home/hdc/data/sequencefile")
    val data=sc.sequenceFile("/home/hdc/data/sequencefile",classOf[org.apache.hadoop.io.Text],classOf[org.apache.hadoop.io.Writable]).map{
          case (x,y)=>(x.toString,y.toString)
      }
    data.foreach(x=>println(x._1+" "+x._2))


  }
}
