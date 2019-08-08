package scala_learn.info_trait

object similar_inteface {

  trait sayHello{
    def sayHello(name:String)
  }

  trait MakeFriends{
    def makefriends(p:person)
  }

  class person(val name:String)extends sayHello with MakeFriends{
    def sayHello(otherName:String): Unit ={ //实现trait中的sayHello方法
      println("sayHello "+name)
    }
    def makefriends(p: person): Unit = {  //实现trait中的makefriends方法
      println(name+" makefriends "+p.name)
    }
  }

  def main(args: Array[String]): Unit = {
    val p1=new person("hdc")
    p1.sayHello("lsg")
    val p2=new person("lsg")
    p2.sayHello("hdc")
    p1.makefriends(p2)
  }
}
