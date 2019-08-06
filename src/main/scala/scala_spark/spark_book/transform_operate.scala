package scala_spark.spark_book

import org.apache.spark.{SparkConf, SparkContext}

object transform_operate {
  def main(args: Array[String]): Unit = {
//    map()
//      filter()
//      flatmap()
//      groupByKey()
//    reduceByKey()
//    sortByKey()
//    join_cogroup()
    combinerByKey()
  }

  //map算子
  def map(): Unit ={
    val conf=new SparkConf().setAppName("map").setMaster("local")
    val sc=new SparkContext(conf)
    val intputRDD=Array(1,2,3,4)
    val resultRDD=intputRDD.map(_*2)
    resultRDD.foreach(x=>println("Map:"+x))
  }

  //filter算子
  def filter():Unit={
    val conf=new SparkConf().setAppName("filter").setMaster("local")
    val sc=new SparkContext(conf)
    val inputRDD=Array(10,12,13,14,15,16,17,18,19,20)
    val resultRDD=inputRDD.filter(x=>x%2==0)
    resultRDD.foreach(x=>println("Filter:"+x))
  }

  //flatmap算子
  def flatmap(): Unit ={
    val conf=new SparkConf().setAppName("filter").setMaster("local")
    val sc=new SparkContext(conf)
    val list=List("hdc I love you","how about you")
    val inputRDD=sc.parallelize(list)
    val resultRDD=inputRDD.flatMap(line=>line.split(" "))
    resultRDD.foreach(word=>println("word："+word))
  }

  //groupByKey算子
  def groupByKey():Unit={
    val conf=new SparkConf().setAppName("groupByKey").setMaster("local")
    val sc=new SparkContext(conf)
    val scorelist=Array(Tuple2("class1",91),Tuple2("class2",82),Tuple2("class1",71),new Tuple2("class2",62))
    val inputRDD=sc.parallelize(scorelist)
    val groupRDD=inputRDD.groupByKey()
    groupRDD.foreach(x=>{
      println("班级："+x._1)
      x._2.foreach(y=>println(y))
    })
  }
  //reduceByKey算子
  def reduceByKey():Unit={
    val conf=new SparkConf().setAppName("reduceByKey算子").setMaster("local")
    val sc=new SparkContext(conf)
    val scorelist=Array(Tuple2("class1",91),Tuple2("class2",82),Tuple2("class1",71),new Tuple2("class2",62))
    val inputRDD=sc.parallelize(scorelist)
    val totalRDD=inputRDD.reduceByKey(_+_);
    totalRDD.foreach(x=>println("班级："+x._1+"成绩："+x._2))
  }

  //sortByKey算子
  def sortByKey():Unit={
    val conf=new SparkConf().setAppName("sortByKey").setMaster("local")
    val sc=new SparkContext(conf)
    val scorelist=Array(Tuple2(100,"hdc"),Tuple2(89,"zhangsan"),Tuple2(96,"lisi"),Tuple2(90,"Tom"),Tuple2(67,"Sam"))
    val inputRDD=sc.parallelize(scorelist)
    val sortRDD=inputRDD.sortByKey()
    sortRDD.foreach(x=>println("姓名："+x._2+" 成绩："+x._1))
  }

  //join和cogroup操作
  def join_cogroup():Unit={
    val conf=new SparkConf().setAppName("join_cogroup").setMaster("local")
    val sc=new SparkContext(conf)

    val stuList=Array(
      Tuple2(1,"hdc"),
      Tuple2(1,"Mary"),
      Tuple2(3,"Tom"),
      Tuple2(4,"Sam"),
      Tuple2(2,"Lisi")
    )
    val scoreList=Array(
      Tuple2(1,100),
      Tuple2(2,90),
      Tuple2(3,89),
      Tuple2(4,99),
      Tuple2(2,91),
      Tuple2(3,88)
    )

    val stuRDD=sc.parallelize(stuList)
    val scoreRDD=sc.parallelize(scoreList)
    //join
    val stu_join_score=stuRDD.join(scoreRDD)
    stu_join_score.foreach(x=>println("ID："+x._1+"姓名："+x._2._1+" 成绩："+x._2._2))
    //cogroup
    val stu_cogroup_score=stuRDD.cogroup(scoreRDD)
    stu_cogroup_score.foreach(
      x=>println("ID:"+x._1+" 姓名："+x._2._1+" 成绩："+x._2._2)
    )
  }

  //combinerByKey算子
  def combinerByKey():Unit={
    val conf=new SparkConf().setAppName("combinerByKey").setMaster("local")
    val sc=new SparkContext(conf)
    val initScore=Array(("Fred", 88.0), ("Fred", 95.0), ("Fred", 91.0), ("Wilma", 93.0), ("Wilma", 95.0), ("Wilma", 98.0))
    val scoreRDD=sc.parallelize(initScore)
    type MVtype=(Int,Double)
    val avgRDD=scoreRDD.combineByKey(
      score=>(1,score),
      (c1:MVtype,score)=>(c1._1+1,c1._2+score),
      (c1:MVtype,c2:MVtype)=>(c1._1+c2._1,c1._2+c2._2)
    ).map{case(name,(num,score))=>(name,score/num)}
    println(avgRDD.collect().mkString(","))
  }


}
