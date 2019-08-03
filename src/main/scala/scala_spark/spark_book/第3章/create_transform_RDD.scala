package scala_spark.spark_book.第3章

import org.apache.spark.{SparkConf, SparkContext}

object create_transform_RDD {
  def main(args: Array[String]): Unit = {
    //创建sparkconf对象，来设置spark的配置置应用信息
    val conf=new SparkConf().setAppName("create_transform_RDD").setMaster("local")
    //SparkContext是Spark所有功能的一个入口,驱动器程序通过SparkContext对象来访问Spark
    val sc=new SparkContext(conf)
    val list=List(("hdc","lsg","Hello World",List("I","am boy")),
      List("Tom","Jim",List("lili","Sam")),("color Type",List("back","blue")))
    println("----------------创建RDD------------------")
    //创建初始的集合RDD有两种方法parallelize和makeRDD
    val parallelizeRDD=sc.parallelize(list)
    println("不指定分区个数那么系统将会自动分区："+parallelizeRDD.partitions.size)
    val makeRDD=sc.makeRDD(list)
    println("系统将会给每个集合对象创建最佳分区："+makeRDD.partitions.size)
    //因为自己电脑原因只能输出分区数为1

    //从文件中读取数据作为RDD
    val textRDD=sc.textFile("/home/hdc/word")
    println("从文件中读取数据创建RDD "+textRDD.partitions.size)

    //对RDD的转化操作即将旧的RDD转化成新的RDD（转化操作）
    // 转化出来的RDD是惰性的求值的，只有在行动操作中用到这些RDD才会被计算。
    val hdcRDD=textRDD.filter(line=>line.contains("hdc"))
    println("转化操作结果："+hdcRDD)

  }
}
