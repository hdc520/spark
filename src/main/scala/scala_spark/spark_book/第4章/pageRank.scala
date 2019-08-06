package scala_spark.spark_book.第4章
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object pageRank {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("pageRank").setMaster("local")
    val sc=new SparkContext(conf)
    val alpha=0.85
    val iterCount=20
    //page
    val pageList=List(
      ("A",List("A","C","D")),
      ("B",List("D")),
      ("C",List("B","D")),
      ("D",List())
    )
    val pageListRDD=sc.parallelize(pageList).partitionBy(new HashPartitioner(2)).persist()
    var ranks=pageListRDD.mapValues(_=>1.0)
    ranks.foreach(x=>println("Key:"+x._1+" value:"+x._2))
    for(i<- 0 until(iterCount)){
      val contributions=pageListRDD.join(ranks).flatMap{
        case(_,(list,rank))=>list.map(dest=>(dest,rank/list.length))
      }
      ranks=contributions.reduceByKey((x,y)=>x+y).mapValues(v=>{
        (1-alpha)+alpha*v
      })
    }
    ranks.sortByKey().foreach(println)
//    contributions.foreach(x=>println("Key:"+x._1+" value:"+x._2))
  }
}
