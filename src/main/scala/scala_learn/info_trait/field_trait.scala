package scala_learn.info_trait

object field_trait {
  trait person{
    val eysNum:Int=2
  }

  class student(val name:String)extends person{
    def sayHello: Unit ={
      println(name+" "+eysNum)
    }
  }

  def main(args: Array[String]): Unit = {
    val s=new student("hdc")
    s.sayHello
  }
}
