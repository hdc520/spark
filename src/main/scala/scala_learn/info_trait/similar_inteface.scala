package scala_learn.info_trait

trait sayHello{
  def sayHello(name:String)
}

trait MakeFriends{
  def makefriends(p:person)
}

class person(val name:String)extends sayHello with MakeFriends{
  override def sayHello(otherName:String): Unit ={
    println("sayHello "+name)
  }

  def makefriends(p: person): Unit = {
    println(name+" makefriends "+p.name)
  }
}

object similar_inteface {
  def main(args: Array[String]): Unit = {
    val p1=new person("hdc")
    p1.sayHello("lsg")
    val p2=new person("lsg")
    p2.sayHello("hdc")
    p1.makefriends(p2)
  }
}
