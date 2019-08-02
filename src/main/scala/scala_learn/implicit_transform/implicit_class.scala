package scala_learn.implicit_transform

import scala_learn.match_operation.match_class.student

//scala会自动使用隐式转换函数，隐式转换函数与普通函数唯一的语法区别就是要以implicit开头，最好返回类型

object implicit_class {

  class SpecialPerson(val name:String)
  class student(val name:String)
  class older(val name:String)

  implicit def Object2SpecialPerson(obj:Object):SpecialPerson={
    if(obj.getClass==classOf[student]){
      val stu=obj.asInstanceOf[student]
      new SpecialPerson(stu.name)
    }
    else if(obj.getClass==classOf[older]){
      val o=obj.asInstanceOf[older]
      new SpecialPerson(o.name)
    }
    else Nil
  }
  val TicketNum=0
  def buyspecialTicket(p:SpecialPerson):String={

    "T-"+TicketNum

  }
  def main(args: Array[String]): Unit = {
    var TicketNum=0
    val s=new student("hdc")
    val o=new older("lisi")
    buyspecialTicket(s,TicketNum)
    buyspecialTicket(o,TicketNum)
  }

}
