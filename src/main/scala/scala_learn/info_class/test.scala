package scala_learn.info_class

object test {
  class student(var name:String,var age:Int){
    println("主构造器开始:")

    println("主构造器结束:")
  }
  def main(args: Array[String]): Unit = {
    println("主构造器实例：")
    val s1=new student("hdc",23)
  }
}
