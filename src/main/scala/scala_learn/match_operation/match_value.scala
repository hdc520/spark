package scala_learn.match_operation

//match case 类似于java中的switch case
//形式：match{ case 值 =>语法}只要满足其中一个case分支就直接返回，不会在继续执行match里语句
//当所有情况都不满足时，使用下划线_代替。

object match_value {
  def JudgeGrade1(grade:String): Unit ={
    grade match {
      case "A" =>println("excellent")
      case "B" =>println("Good")
      case "C" =>println("Just so so")
      case _   =>println("bad")
    }
  }

  def JudgeGrade2(grade:String,name:String):Unit={
    grade match {
      case "A"=>println("excellent")
      case "B"=>println("Good")
      case "C" if name=="zh" =>println(name+" "+grade)
      case "C" if name=="ls" =>println(name+" "+grade)
      case "C" =>println("Just so so")
      case _ if name=="leo"=>println("very bad")
      case _   =>println(name+" bad")
    }
  }

  def JudgeGrade3(grade:String): Unit ={
    grade match {
      case "A" =>println("excellent")
      case "B" =>println("Good")
      case "C" =>println("Just so so")
      case badgrade=>println(badgrade+" bad")
    }
  }

  def main(args:Array[String]): Unit ={
    JudgeGrade1("A")
    JudgeGrade1("B")
    JudgeGrade1("C")
    JudgeGrade1("D")
    println("---------case里加if-----------")
    JudgeGrade2("A","hdc")
    JudgeGrade2("B","zs")
    JudgeGrade2("C","ww")
    JudgeGrade2("C","ls")
    JudgeGrade2("C","zh")
    JudgeGrade2("D","leo")
    JudgeGrade2("E","Tom")
    println("---------输出case后的值---------")
    JudgeGrade3("A")
    JudgeGrade3("B")
    JudgeGrade3("C")
    JudgeGrade3("D")

  }
}
