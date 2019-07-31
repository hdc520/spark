package scala_learn.info_class

import scala.collection.mutable.ArrayBuffer

class Class{
  class student(val name:String){}  //内部类
  val stu=new ArrayBuffer[student]()
  def getStu(name:String):student={
    new student(name)
  }
}

object inner_calss {
  def main(args: Array[String]): Unit = {
    val c1=new Class
    val s1=c1.getStu("hdc")
    c1.stu+=s1
    println(c1.stu(0))

    val c2=new Class
    val s2=c2.getStu("lsg")
//    c1.stu+=c2  错误
    c2.stu+=s2


  }
}
