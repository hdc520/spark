package scala_learn.match_operation



object match_Array_List {
  def greetingArray(arr:Array[String]): Unit ={
    arr match {
      case Array("hdc")=>println("Hi,"+arr.mkString)
      case Array("hdc","lsg")=>println("Hi,"+"hdc,lsg")
      case Array("leo",_*)=>println("Hi,leo and your friends")
      case _ =>println("Hi,other fiends")
    }
  }

  def greetingList(list:List[String]): Unit ={
    list match{
      case "leo"::Nil=>println("Hi,leo!!！")
      case "hdc"::"lsg"::Nil=>println("Hi,hdc and lsg")
      case "leo"::tail=>println("Hi,leo and your friends")
      case _=>println("other friends")
    }
  }


  def main(args: Array[String]): Unit = {
    println("---------Array的模式匹配---------")
    greetingArray(Array("hdc"))
    greetingArray(Array("hdc","lsg"))
    greetingArray(Array("leo","hdc","lsg"))
    greetingArray(Array("we","leo","hdc","lsg"))
    greetingArray(Array("zs","ls"))
    println("---------List的模式匹配---------")
    greetingList(List("hdc"))
    greetingList(List("hdc","lsg"))
    greetingList(List("hdc","leo"))
    greetingList(List("leo","hdc"))


  }
}
