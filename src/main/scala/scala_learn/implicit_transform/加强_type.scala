package scala_learn.implicit_transform

object 加强_type {

  class Man(name:String)
  class SuperMan(name:String){
    def start: Unit ={
      println("superman")
    }
  }

  implicit def Man2Superman(man:Man):SuperMan={
    println(man.toString)
    new SuperMan(man.toString)
  }

  def main(args: Array[String]): Unit = {
    val leo=new Man("leo")
    leo.start
  }
}
