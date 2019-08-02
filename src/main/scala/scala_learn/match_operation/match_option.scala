package scala_learn.match_operation

object match_option {

  def getGrade(name:String,grades:Map[String,String]): Unit ={
    val grade=grades.get(name)
    grade match {
      case Some(grade)=>println(name+" 成绩为："+grade)
      case None=>println("没有 "+name+" 的成绩")
    }
  }

  def main(args: Array[String]): Unit = {
    val grades=Map("hdc"->"A","zs"->"B","ls"->"C")
    getGrade("hdc",grades)
    getGrade("zs",grades)
    getGrade("tom",grades)
  }

}
