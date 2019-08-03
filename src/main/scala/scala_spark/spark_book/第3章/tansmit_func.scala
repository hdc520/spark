//package scala_spark.spark_book.第3章
//
//import org.apache.spark.rdd.RDD
//
//object tansmit_func {
//  class SearchFunc(val query:String){
//    def isMatch(s:String):Boolean={
//      s.contains(query)
//    }
//
//    def getMatchFunc(rdd:RDD[String]):RDD[String]={
//      rdd.map(isMatch)
//    }
//  }
//
//  def main(args: Array[String]): Unit = {
//
//  }
//
//}
