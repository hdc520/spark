package scala_learn.match_operation

object match_class {
  class person
  case class teacher(name:String,subject:String)extends person
  case class student(name:String,classroom:String)extends person

  def matchclass(p:person): Unit ={
    p match {
      case teacher(name,subject)=>println(name+" "+subject+" 老师")
      case student(name,classroom)=>println(name+" "+classroom+" 学生")
      case _=>println("校外人")
    }
  }

  def main(args: Array[String]): Unit = {
    matchclass(new person)
    matchclass(new student("hdc","1001"))
    matchclass(new teacher("Tom","math"))
  }
}
