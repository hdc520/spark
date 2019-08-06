package scala_spark.spark_book.第4章
import org.apache.spark.{Partitioner, SparkConf, SparkContext}
object Mypartitioner {
  class myPartitioner(numpairs:Int)extends Partitioner{
    override def numPartitions: Int = numpairs
    override def getPartition(key: Any): Int = {
      var code=key.hashCode()%numPartitions
      if(code<0){
        code+numPartitions
      }else{
        code
      }
    }

    override def hashCode(): Int = numPartitions

    override def equals(obj: Any): Boolean = obj match {
      case dnp:myPartitioner=>dnp.numPartitions==numPartitions
      case _=>false
    }
  }

  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("Mypartitioner").setMaster("local")
    val sc=new SparkContext(conf)
    val input = "/home/hdc/page"
    val data = sc.textFile(input)
    val rdd = data.map(line => (line.split(" ")(0).toInt, line))
    val rdd1=rdd.partitionBy(new myPartitioner(5))
    println(rdd1.collect().mkString(","))
    sc.stop()

  }
}
